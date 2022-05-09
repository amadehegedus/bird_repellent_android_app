package hu.bme.aut.moblab.birdrepellent.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.moblab.birdrepellent.network.SyncApi
import hu.bme.aut.moblab.birdrepellent.network.XenoCantoApi
import hu.bme.aut.moblab.birdrepellent.util.SYNC_BASE_URL
import hu.bme.aut.moblab.birdrepellent.util.XENO_CANTO_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    @Named("XenoCanto")
    fun provideRetrofitForXenoCanto(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(XENO_CANTO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    @Named("Sync")
    fun provideRetrofitForSync(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(SYNC_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideXenoCantoApi(@Named("XenoCanto") retrofit: Retrofit) = retrofit.create(XenoCantoApi::class.java)

    @Provides
    @Singleton
    fun provideSyncApi(@Named("Sync") retrofit: Retrofit) = retrofit.create(SyncApi::class.java)
}