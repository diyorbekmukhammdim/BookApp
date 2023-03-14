package uz.gita.bookappflow.domain.imp

import uz.gita.bookappflow.data.local.BookStatus
import uz.gita.bookappflow.data.local.UsersBookStatus
import uz.gita.bookappflow.data.local.room.daos.OwnBooksDao
import uz.gita.bookappflow.data.local.room.daos.UsersBookDao
import uz.gita.bookappflow.data.local.room.entities.OwnBooksEntity
import uz.gita.bookappflow.data.local.room.entities.UsersBooksEntity
import uz.gita.bookappflow.data.local.room.mappers.mapToOwnBookEntity
import uz.gita.bookappflow.data.network.models.request.ChangeLikeRequest
import uz.gita.bookappflow.data.network.models.response.BookResponse
import uz.gita.bookappflow.domain.OfflineBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineBookRepositoryImp @Inject constructor(private val usersBookDao: UsersBookDao, private val ownBookDao: OwnBooksDao) :
    OfflineBookRepository {
    override suspend fun addBook(bookResponse: BookResponse, status: BookStatus) {
        val temp = BookResponse(bookResponse.id, bookResponse.title, bookResponse.author, bookResponse.description, bookResponse.pageCount, false)
        ownBookDao.insert(temp.mapToOwnBookEntity(status))
    }

    override suspend fun addBook(list: List<BookResponse>) {
        ownBookDao.insert(list.map { it.mapToOwnBookEntity(BookStatus.ONLINE) })
    }

    override suspend fun addUsersBookList(list: List<UsersBooksEntity>, userId: Int) {
        usersBookDao.insert(list)
    }

    override suspend fun updateBook(updateBookRequest: OwnBooksEntity) {
        ownBookDao.insert(updateBookRequest)
    }

    override suspend fun getAllBooks(): List<OwnBooksEntity> {
        return ownBookDao.getAllBooks()
    }

    override suspend fun deleteBook(bookId: Int, status: BookStatus) {
        if (status == BookStatus.OFFLINE_DELETE) {
            val book = ownBookDao.getBookById(bookId)
            if (book != null) {
                book.status = status.value
                ownBookDao.update(book)
            }
        } else {
            ownBookDao.deleteById(bookId)
        }
    }

    override suspend fun deleteBook(book: OwnBooksEntity) {
        ownBookDao.delete(book)
    }

    override suspend fun addToFavourite(bookId: Int, status: BookStatus) {
        val book = ownBookDao.getBookById(bookId)
        if (book != null) {
            book.fav = true
            book.status = status.value
            ownBookDao.update(book)
        }
    }

    override suspend fun getBooksByUser(userId: Int): List<UsersBooksEntity> {
        return usersBookDao.getBooksByUserId(userId)
    }

    override suspend fun getAllUsersBook(): List<UsersBooksEntity> {
        return usersBookDao.getAllUsersBooks()
    }

    override suspend fun setLikeBook(changeLikeRequest: ChangeLikeRequest, status: UsersBookStatus) {
        val book = usersBookDao.getBookById(changeLikeRequest.bookId)
        if (book != null) {
            book.isLike = changeLikeRequest.isLike
            if (changeLikeRequest.isLike) {
                book.status = status.value
            } else {
                book.status = status.value
            }
            usersBookDao.update(book)
        }
    }

    override suspend fun clearOwnBookTable() {
        ownBookDao.deleteTable()
    }

    override suspend fun getOfflineChangedOwnBook(status: BookStatus): List<OwnBooksEntity> {
        return ownBookDao.getOfflineChangedBooks().filter { it.status == status.value }
    }

    override suspend fun getOfflineChangedUsersBook(status: UsersBookStatus): List<UsersBooksEntity> {
        return usersBookDao.getAllUsersBooksChanged(status)
    }

    override suspend fun getOwnBookById(bookId: Int): OwnBooksEntity? {
        return ownBookDao.getBookById(bookId)
    }

    override suspend fun getUsersBookById(bookId: Int): UsersBooksEntity? {
        return usersBookDao.getBookById(bookId)
    }
}