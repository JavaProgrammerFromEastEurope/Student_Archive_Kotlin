package application.util

import java.util.*

object Input {
    private val scanner = Scanner(System.`in`)
    private var inputObject: Any? = null
    val integer: Int
        get() {
            if (scanner.hasNextInt()) {
                inputObject = scanner.nextInt()
                if (inputObject as Int >= 0) return inputObject as Int
            }
            warningString
            return integer
        }

    fun getInteger(message: String?): Int {
        println(message)
        return integer
    }

    val string: String
        get() = scanner.next()

    fun getString(message: String?): String {
        println(message)
        return string
    }

    fun anyIntInitialize(): Int {
        if (scanner.hasNextInt()) {
            inputObject = scanner.nextInt()
            return inputObject as Int
        }
        warningString
        return anyIntInitialize()
    }

    fun inputPosition(bound: Int): Int {
        if (scanner.hasNextInt()) {
            inputObject = scanner.nextInt()
            if (inputObject as Int in 1..bound)
                return inputObject as Int
        }
        warningString
        return inputPosition(bound)
    }

    fun inputPosition(message: String?, bound: Int): Int {
        println(message)
        return inputPosition(bound)
    }

    fun inputCharacter(): String? {
        if (scanner.hasNext("[a-zA-Z]")) {
            inputObject = scanner.next()
            return inputObject as String?
        }
        warningString
        return inputCharacter()
    }

    fun doubleInitialize(): Double {
        if (scanner.hasNextDouble()) {
            inputObject = scanner.nextDouble()
            if (inputObject as Double > 0) return inputObject as Double
        }
        warningString
        return doubleInitialize()
    }

    private val warningString: Unit
        get() {
            if (inputObject == null) inputObject = scanner.next()
            val repeatInput = "Please enter the correct data: "
            val warningMessage = "this is an invalid value!"
            System.out.printf(" %s - %s %n %s", inputObject, warningMessage, repeatInput)
            inputObject = null
        }
}