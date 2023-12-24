package com.dscoding.openaicompletion.data

import com.dscoding.openaicompletion.common.Result
import com.dscoding.openaicompletion.data.model.ChatCompletionRequest
import com.dscoding.openaicompletion.domain.OpenAiApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OpenAiApiNetworkRepository(private val openAiApi: OpenAiApi) : OpenAiApiRepository {

    override fun getChatCompletion(
        apiKey: String,
        requestBody: ChatCompletionRequest
    ): Flow<Result<String>> =
        flow {
            emit(Result.Loading())
            try {
                val chatCompletion = openAiApi.getChatCompletion("Bearer $apiKey", requestBody)
                val outputMessage = chatCompletion.choices[0].message.content
                if (outputMessage.isNotEmpty()) {
                    emit(Result.Success(outputMessage))
                } else {
                    emit(Result.Error("Error Empty response"))
                }
            } catch (throwable: Throwable) {
                emit(Result.Error(throwable.message ?: "Error Calling API"))
            }
        }
}