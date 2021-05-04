package pl.mija.gcp.app.util.xml

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.util.pipeline.*
import io.ktor.utils.io.*
import io.ktor.utils.io.jvm.javaio.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.jvm.jvmErasure


private class XmlConverter(private val xml: XmlMapper) : ContentConverter {
    override suspend fun convertForSend(
        context: PipelineContext<Any, ApplicationCall>,
        contentType: ContentType,
        value: Any
    ): Any {
        return withContext(Dispatchers.IO) {
            TextContent(xml.writeValueAsString(value), contentType.withCharset(context.call.suitableCharset()))
        }
    }

    override suspend fun convertForReceive(context: PipelineContext<ApplicationReceiveRequest, ApplicationCall>): Any? {
        val request = context.subject
        val channel = request.value as? ByteReadChannel ?: return null
        val type = request.typeInfo
        val javaType = type.jvmErasure

        return withContext(Dispatchers.IO) {
            val reader = channel.toInputStream().reader(context.call.request.contentCharset() ?: Charsets.UTF_8)
            val readText = reader.readText()
            xml.readValue(readText, javaType.javaObjectType)
        }
    }
}

fun ContentNegotiation.Configuration.xml(
    contentType: ContentType = ContentType.Application.Xml,
    block: ObjectMapper.() -> Unit = {}
) {
    val mapper = XmlMapper()
    mapper.apply {
        registerKotlinModule()
        setDefaultPrettyPrinter(
            DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            }
        )
    }
    mapper.apply(block)
    register(contentType, XmlConverter(mapper))
}