package uz.gita.bookappflow.di

import android.content.Context
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.bookappflow.data.network.api.BookApi
import uz.gita.bookappflow.data.network.api.UserApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @[Provides Singleton]
    fun getOkHTTPClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context)).build()

    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://143.198.48.222:82")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun getBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    @[Provides Singleton]
    fun getUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @[Provides Singleton]
    fun getGson(): Gson = Gson()
}