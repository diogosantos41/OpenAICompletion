package com.dscoding.openaicompletion.presentation

import androidx.lifecycle.viewModelScope
import com.dscoding.openaicompletion.common.Result
import com.dscoding.openaicompletion.data.model.ChatCompletionRequest
import com.dscoding.openaicompletion.data.model.ChatMessage
import com.dscoding.openaicompletion.domain.OpenAiApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class OpenAiViewModel @Inject constructor(private val repository: OpenAiApiRepository) :
    BaseViewModel<OpenAiState>() {

    private var getChatCompletion: Job? = null

    override fun initialState(): OpenAiState {
        return OpenAiState(
            inputApiKey = "",
            inputPrompt = "",
            message = "",
            isLoading = false
        )
    }

    private fun getChatCompletion() {
        getChatCompletion?.cancel()
        val inputPrompt = listOf(
            ChatMessage(role = "user", content = uiState.value.inputPrompt)
        )
        getChatCompletion = repository.getChatCompletion(
            uiState.value.inputApiKey,
            ChatCompletionRequest(model = "gpt-3.5-turbo", messages = inputPrompt)
        ).onEach { response ->
            when (response) {
                is Result.Loading -> updateState(
                    uiState.value.copy(
                        isLoading = true
                    )
                )

                is Result.Success -> updateState(
                    uiState.value.copy(
                        message = response.data ?: "",
                        isLoading = false
                    )
                )

                is Result.Error -> updateState(
                    uiState.value.copy(
                        message = response.error!!,
                        isLoading = false
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

    fun inputPrompt(input: String) {
        updateState(
            uiState.value.copy(
                inputPrompt = input
            )
        )
    }

    fun inputApiKey(input: String) {
        updateState(
            uiState.value.copy(
                inputApiKey = input
            )
        )
    }

    fun getMessage() {
        getChatCompletion()
    }
}