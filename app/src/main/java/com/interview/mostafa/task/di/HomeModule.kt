package com.interview.mostafa.task.di

import android.content.SharedPreferences
import com.interview.mostafa.task.BuildConfig
import com.interview.mostafa.task.feature_home.data.remote.HomeApi
import com.interview.mostafa.task.feature_home.data.repository.HomeRepository
import com.interview.mostafa.task.feature_home.data.repository.HomeRepositoryImpl
import com.interview.mostafa.task.feature_home.domain.use_case.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeApi(client: OkHttpClient): HomeApi {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/coffee/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(
            homeApi = homeApi,
        )
    }
    @Provides
    fun provideHomeUseCase(repository: HomeRepository): HomeUseCase {
        return HomeUseCase(repository = repository)
    }
}