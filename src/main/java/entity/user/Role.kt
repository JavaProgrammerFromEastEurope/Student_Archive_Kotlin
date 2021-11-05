package entity.user

import java.io.Serializable

class Role private constructor() : Serializable {
    private val roles = HashMap<Int, String>()
    fun getRole(roleName: Int): String? {
        return if (roles.containsKey(roleName)) {
            roles[roleName]
        } else null
    }

    companion object {
        val instance = Role()
    }

    init {
        roles[0] = "Admin"
        roles[1] = "User"
    }
}