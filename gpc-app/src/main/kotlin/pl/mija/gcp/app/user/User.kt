package pl.mija.gcp.app.user

data class UserCreate(val name: String, val lastName: String)
data class User(val id: Long, val name: String, val lastName: String)