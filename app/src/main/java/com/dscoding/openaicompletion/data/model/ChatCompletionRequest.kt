package com.dscoding.openaicompletion.data.model

data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>
)