package com.dscoding.openaicompletion.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T> : ViewModel() {

    private val _uiState by lazy { mutableStateOf(initialState()) }
    val uiState: State<T> = _uiState

    abstract fun initialState(): T

    fun updateState(state: T) {
        _uiState.value = state
    }
}