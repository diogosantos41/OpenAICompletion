package com.dscoding.openaicompletion.data.model

data class ChatCompletionResponse(
    val choices: List<ChatCompletionChoice>,
    val created: Long,
    val id: String,
    val model: String,
    val usage: Map<String, Int> // Adjust the type based on the expected response
)