package uz.gita.bookappflow.utils

interface EventListener<T> {
    fun success(data: T)
    fun error(message: String)
    fun loading(boolean: Boolean)
}