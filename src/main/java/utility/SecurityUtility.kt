package utility

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom
import java.util.*

object SecurityUtility {
    private const val SALT = "salt" /* Salt should be protected carefully */
    private const val SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(12, SecureRandom(SALT.toByteArray()))
    }

    fun randomPassword(): String {
        val salt = StringBuilder()
        val random = Random()
        while (salt.length < 18) {
            val index = (random.nextFloat() * SALTCHARS.length).toInt()
            salt.append(SALTCHARS[index])
        }
        return salt.toString()
    }
}