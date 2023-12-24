package com.dscoding.openaicompletion.di

import com.dscoding.openaicompletion.data.OpenAiApi
import com.dscoding.openaicompletion.data.OpenAiApiNetworkRepository
import com.dscoding.openaicompletion.domain.OpenAiApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOpenAiApi(retrofit: Retrofit): OpenAiApi =
        retrofit.create(OpenAiApi::class.java)

    @Provides
    @Singleton
    fun provideSpRepository(api: OpenAiApi): OpenAiApiRepository {
        return OpenAiApiNetworkRepository(api)
    }
}