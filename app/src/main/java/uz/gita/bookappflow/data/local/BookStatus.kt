package uz.gita.bookappflow.data.local

enum class BookStatus(val value: Int) {
    ONLINE(0), OFFLINE_ADD(1), OFFLINE_DELETE(2),
    OFFLINE_CHANGED(3),OFFLINE_ADD_FAVOURITE(4)
}