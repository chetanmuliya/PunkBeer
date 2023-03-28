package cm.chettas.punkbeer.di

import androidx.viewbinding.BuildConfig
import cm.chettas.punkbeer.data.remote.BeerService
import cm.chettas.punkbeer.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun getHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }else{
            OkHttpClient
                .Builder()
                .build()
        }
    }

    @Singleton
    @Provides
    fun getRetrofit(okkHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okkHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun getBeerService(retrofit: Retrofit) =
        retrofit.create(BeerService::class.java)
}