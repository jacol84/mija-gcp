package pl.mija.gcp.app.controller

import java.util.*

data class MessageValid (val message:String, val ref:UUID = UUID.randomUUID())