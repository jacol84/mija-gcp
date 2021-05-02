package pl.mija.gcp.app.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <R : Any> R.mijaLogger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(this::class.java.name) }
}