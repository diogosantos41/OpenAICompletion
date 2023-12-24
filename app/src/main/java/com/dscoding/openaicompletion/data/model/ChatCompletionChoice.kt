package com.dscoding.openaicompletion.data.model

data class ChatCompletionChoice(
    val finish_reason: String,
    val index: Int,
    val message: ChatMessage,
    val logprobs: Any? // Adjust the type based on the expected response
)