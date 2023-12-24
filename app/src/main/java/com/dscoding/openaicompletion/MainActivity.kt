package com.dscoding.openaicompletion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dscoding.openaicompletion.presentation.OpenAiViewModel
import com.dscoding.openaicompletion.ui.theme.OpenAICompletionTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OpenAICompletionTheme {
                val keyboardController = LocalSoftwareKeyboardController.current
                val viewModel = hiltViewModel<OpenAiViewModel>()
                val state = viewModel.uiState.value
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp, vertical = 30.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "OpenAI App", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "Input API Key")
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        value = state.inputApiKey,
                        onValueChange = { viewModel.inputApiKey(it) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Input prompt")
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        value = state.inputPrompt,
                        onValueChange = { viewModel.inputPrompt(it) }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        keyboardController?.hide()
                        viewModel.getMessage()
                    }) {
                        Text(text = "Get Response")
                    }
                    if (state.isLoading) {
                        Spacer(modifier = Modifier.height(20.dp))
                        CircularProgressIndicator()
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(Color.LightGray.copy(alpha = 0.3f))
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = state.message,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Start,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}
