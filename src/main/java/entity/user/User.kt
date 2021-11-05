package entity.user

import java.io.Serializable

class User(var userName: String?, var password: String) : Serializable {
    var id = 0L
    var role: String? = Role.instance.getRole(0)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as User
        return userName == user.userName && password == user.password
    }

    override fun hashCode(): Int {
        var result = userName?.hashCode() ?: 0
        result = 31 * result + password.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (role?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "User(id=$id, userName=$userName, password='$password',role=$role)"
    }
}