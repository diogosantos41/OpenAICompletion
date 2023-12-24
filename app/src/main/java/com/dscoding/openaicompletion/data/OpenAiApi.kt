package com.dscoding.openaicompletion.data

import com.dscoding.openaicompletion.data.model.ChatCompletionRequest
import com.dscoding.openaicompletion.data.model.ChatCompletionResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAiApi {

    @POST("chat/completions")
    suspend fun getChatCompletion(
        @Header("Authorization") authorization: String,
        @Body requestBody: ChatCompletionRequest
    ): ChatCompletionResponse

}