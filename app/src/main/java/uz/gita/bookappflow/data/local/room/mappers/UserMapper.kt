package uz.gita.bookappflow.data.local.room.mappers

import uz.gita.bookappflow.data.local.room.entities.UserEntity
import uz.gita.bookappflow.data.network.models.response.UserResponse

fun UserResponse.mapToUserEntity(): UserEntity {
    return UserEntity(this.id, this.firstName, this.lastName)
}

fun UserEntity.mapToUserResponse(): UserResponse {
    return UserResponse(this.id, this.firstName, this.lastName)
}