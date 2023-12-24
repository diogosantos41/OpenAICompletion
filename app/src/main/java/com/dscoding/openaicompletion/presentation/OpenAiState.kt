package com.dscoding.openaicompletion.presentation

data class OpenAiState(
    val inputApiKey: String,
    val inputPrompt: String,
    val message: String,
    val isLoading: Boolean
)