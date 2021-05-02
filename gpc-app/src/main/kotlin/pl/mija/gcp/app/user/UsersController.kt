package pl.mija.gcp.app.user


internal val mutableList = mutableListOf<User>()

internal class UsersController {
    fun getAll(): List<User> {
        return mutableList.toList()
    }

    fun getById(id: Long): User? {
        return mutableList.find { it.id == id }
    }

    fun create(user: UserCreate): User {
        val element = User(id.incrementAndGet(), user.name, user.lastName)
        mutableList.add(element)
        return element
    }

    fun put(id: Long, user: User): User {
        mutableList.removeIf { it.id == id }
        mutableList.add(user)
        return user
    }

    fun delete(id: Long): Boolean {
        return mutableList.removeIf { it.id == id }
    }

}