package pl.mija.gcp.app.user

internal data class UserCreate(val name: String, val lastName: String)
internal data class User(val id: Long, val name: String, val lastName: String)