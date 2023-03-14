package uz.gita.bookappflow.data.local.room.mappers

import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.data.network.models.request.AddBookRequest
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.data.network.models.response.BooksByUserResponse

fun BookResponse.mapToOwnBookEntity(status: BookStatus): OwnBooksEntity {
    return OwnBooksEntity(this.id, this.title, this.author, this.description, this.pageCount, this.fav, status = status.value)
}


fun OwnBooksEntity.mapToBookResponse(): BookResponse {
    return BookResponse(this.id, this.title, this.author, this.description, this.pageCount, this.fav)
}

fun BooksByUserResponse.mapToUsersBookEntity(userId: Int): UsersBooksEntity {
    return UsersBooksEntity(
        this.id,
        this.title,
        this.author,
        this.description,
        this.pageCount,
        this.fav,
        this.isLike,
        this.likeCount,
        this.disLikeCount,
        userId
    )
}

fun OwnBooksEntity.toMapAddBookRequest(): AddBookRequest {
    return AddBookRequest(this.title, this.author, this.description, this.pageCount)
}

fun AddBookRequest.mapToOwnBookEntity(status: BookStatus): OwnBooksEntity {
    return OwnBooksEntity(0, this.title, this.author, this.description, this.pageCount, false, status = status.value)
}

fun AddBookRequest.mapToBookResponse(): BookResponse {
    return BookResponse(0, this.title, this.author, this.description, this.pageCount, false)
}