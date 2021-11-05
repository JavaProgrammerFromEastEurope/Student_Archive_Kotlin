package controllers

interface IControllerTemplate {
    fun addObject()
    val objects: Unit
    fun updateObject()
    fun removeObject()
    fun sortByStringObject()
    fun sortByIntObject()
}