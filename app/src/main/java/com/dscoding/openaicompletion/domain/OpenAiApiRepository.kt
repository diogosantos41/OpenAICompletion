package com.dscoding.openaicompletion.domain

import com.dscoding.openaicompletion.common.Result
import com.dscoding.openaicompletion.data.model.ChatCompletionRequest
import kotlinx.coroutines.flow.Flow

interface OpenAiApiRepository {
    fun getChatCompletion(
        apiKey: String,
        requestBody: ChatCompletionRequest
    ): Flow<Result<String>>
}