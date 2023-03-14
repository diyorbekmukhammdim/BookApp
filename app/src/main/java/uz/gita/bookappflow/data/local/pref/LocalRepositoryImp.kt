package uz.gita.bookappflow.data.local.pref

import android.content.SharedPreferences
import uz.gita.bookappflow.data.network.models.request.SigUpRequest
import uz.gita.bookappflow.data.network.models.request.SignInRequest

class LocalRepositoryImp constructor(private val pref: SharedPreferences) : LocalRepository {
    companion object {
        private const val TEMPORARY_TOKEN = "TEMPORARY_TOKEN"
        private const val USER_PHONE = "USER_PHONE"
        private const val USER_PASSWORD = "USER_PASSWORD"
        private const val IS_SUCCESS_LOGIN = "IS_SUCCESS_LOGIN"
        private const val VERIFY_TYPE = "VERIFY_TYPE"
        private const val FIRST_NAME = "FIRST_NAME"
        private const val LAST_NAME = "LAST_NAME"
        private const val PHONE = "PHONE"
        private const val PASSWORD = "PASSWORD"
    }

    override suspend fun saveTemporaryToken(token: String) {
        pref.edit().putString(TEMPORARY_TOKEN, token).apply()
    }

    override suspend fun getTemporaryToken(): String {
        return pref.getString(TEMPORARY_TOKEN, "") ?: ""
    }

    override suspend fun saveTempUser(data: SignInRequest) {
        pref.edit().putString(USER_PHONE, data.phone).putString(USER_PASSWORD, data.password).apply()
    }

    override suspend fun saveUser(user: SigUpRequest) {
        pref.edit().putString(FIRST_NAME, user.firstName)
            .putString(LAST_NAME, user.lastName)
            .putString(PASSWORD, user.password)
            .putString(PHONE, user.phone)
            .apply()
    }

    override suspend fun deleteUser() {
        pref.edit().putString(FIRST_NAME, "")
            .putString(LAST_NAME, "")
            .putString(PASSWORD, "")
            .putString(PHONE, "").apply()
    }

    override suspend fun getUser(): SigUpRequest {
        return SigUpRequest(
            pref.getString(PHONE, "") ?: "",
            pref.getString(PASSWORD, "") ?: "",
            pref.getString(LAST_NAME, "") ?: "",
            pref.getString(FIRST_NAME, "") ?: ""
        )
    }

    override suspend fun getTempUser(): SignInRequest {
        return SignInRequest(pref.getString(USER_PHONE, "") ?: "", pref.getString(USER_PASSWORD, "") ?: "")
    }

    override suspend fun deleteTempUser() {
        pref.edit().putString(USER_PHONE, "").putString(USER_PASSWORD, "").apply()
    }


    override suspend fun deleteTemporaryToken() {
        saveTemporaryToken("")
    }

    override suspend fun setVerifyType(b: Boolean) {
        pref.edit().putBoolean(VERIFY_TYPE, b).apply()
    }

    override suspend fun isSignUpType(): Boolean {
        return pref.getBoolean(VERIFY_TYPE, false)
    }

    override suspend fun saveSuccessLogin(boolean: Boolean) {
        pref.edit().putBoolean(IS_SUCCESS_LOGIN, boolean).apply()
    }

    override suspend fun getSuccessLogin(): Boolean {
        return pref.getBoolean(IS_SUCCESS_LOGIN, false)
    }
}