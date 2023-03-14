package uz.gita.bookappflow.data.local.pref

import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.request.SignInRequest

interface LocalRepository {
    suspend fun saveTemporaryToken(token:String)
    suspend fun getTemporaryToken():String
    suspend fun saveTempUser(data:SignInRequest)
    suspend fun saveUser(user:SigUpRequest)
    suspend fun deleteUser()
    suspend fun getUser():SigUpRequest
    suspend fun getTempUser():SignInRequest
    suspend fun deleteTempUser()
    suspend fun deleteTemporaryToken()
    suspend fun setVerifyType(b:Boolean)
    suspend fun isSignUpType():Boolean
    suspend fun saveSuccessLogin(boolean: Boolean)
    suspend fun getSuccessLogin():Boolean
}