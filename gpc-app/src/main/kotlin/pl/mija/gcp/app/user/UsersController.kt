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
        return user.mapper().also {
            mutableList.add(it)
        }
    }

    fun put(id: Long, user: UserEdit): User {
        mutableList.removeIf { it.id == id }

        val validate = user.validateModel()
        println(
            """
            
            /////////aaa
            $validate
            /////////aaa
            
            """.trimIndent()
        )
        return user.mapper().also {
            mutableList.add(it)
        }
    }

    fun delete(id: Long): Boolean {
        return mutableList.removeIf { it.id == id }
    }

    fun getParams(): UserParams {
        val userParams = UserParams()
        userParams.rules = mapOf("name" to "required")
        return userParams

    }

}


