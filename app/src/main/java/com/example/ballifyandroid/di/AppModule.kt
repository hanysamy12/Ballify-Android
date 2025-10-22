package com.example.ballifyandroid.di

import com.example.ballifyandroid.data.RepoImp
import com.example.ballifyandroid.data.remote.ApiService
import com.example.ballifyandroid.data.remote.AuthInterceptor
import com.example.ballifyandroid.data.remote.IRemoteDataSource
import com.example.ballifyandroid.data.remote.RemoteDataSourceImp
import com.example.ballifyandroid.domain.repo.IRepo
import com.example.ballifyandroid.domain.usecase.GetFixturesUseCase
import com.example.ballifyandroid.domain.usecase.GetSportLeaguesUseCase
import com.example.ballifyandroid.domain.usecase.GetTeamsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://apiv2.allsportsapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): IRemoteDataSource =
        RemoteDataSourceImp(apiService)

    @Provides
    @Singleton
    fun provideRepository(iRemoteDataSource: IRemoteDataSource): IRepo = RepoImp(iRemoteDataSource)

    @Provides
    @Singleton
    fun provideGetSportLeaguesUseCase(iRepo: IRepo): GetSportLeaguesUseCase {
        return GetSportLeaguesUseCase(iRepo)
    }

    @Provides
    @Singleton
    fun provideGetFixturesUseCase(iRepo: IRepo): GetFixturesUseCase{
        return GetFixturesUseCase(iRepo)
    }

    @Provides
    @Singleton
    fun provideGetTeamsUseCase(iRepo: IRepo): GetTeamsUseCase{
        return GetTeamsUseCase(iRepo)
    }



}