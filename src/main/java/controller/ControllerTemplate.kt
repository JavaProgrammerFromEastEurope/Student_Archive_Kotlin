package controller

interface ControllerTemplate {
    fun addObject()
    val objects: Unit
    fun updateObject()
    fun removeObject()
    fun sortByStringObject()
    fun sortByIntObject()
}