package network

import java.io.*

class Message : Serializable {
    var name: String
    var data: ByteArray?

    private constructor(name: String, data: ByteArray) {
        this.name = name
        this.data = data
    }

    private constructor(name: String) {
        this.name = name
        data = null
    }

    fun hasData(): Boolean {
        return data!!.isNotEmpty()
    }

    companion object {
        fun create(name: String, data: ByteArray): Message {
            return Message(name, data)
        }

        @JvmStatic
        fun create(name: String): Message {
            return Message(name)
        }

        @JvmStatic
        fun makeMessageWithBytes(name: String, item: Any): Message? {
            try {
                val bos = ByteArrayOutputStream()
                val oos = ObjectOutputStream(bos)
                oos.writeObject(item)
                return create(name, bos.toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        @JvmStatic
        fun makeObjectFromMessage(message: Message): Any? {
            try {
                if (message.data != null) {
                    val bis = ByteArrayInputStream(message.data)
                    val ois = ObjectInputStream(bis)
                    return ois.readObject()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            return null
        }
    }
}