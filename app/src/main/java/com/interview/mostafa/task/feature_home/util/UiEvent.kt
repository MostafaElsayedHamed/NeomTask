package com.interview.mostafa.task.feature_home.util

import com.interview.mostafa.task.core.UiText

sealed class UiEvent {
    data class ShowToast(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}