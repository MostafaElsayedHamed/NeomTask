package com.interview.mostafa.task.feature_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.interview.mostafa.task.core.utli.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val description by mutableStateOf(savedStateHandle.get<String>(Constants.DESCRIPTION) ?: "")
    val image by mutableStateOf(savedStateHandle.get<String>(Constants.IMAGE_URL) ?: "")
    val ingredients by mutableStateOf(JSONArray(savedStateHandle.get<String>(Constants.INGREDIENTS)).toList())
    val title by mutableStateOf(savedStateHandle.get<String>(Constants.TITLE) ?: "")


    @Throws(JSONException::class)
    fun JSONArray.toList(): List<Any> {
        val list = mutableListOf<Any>()
        for (i in 0 until this.length()) {
            var value: Any = this[i]
            when (value) {
                is JSONArray -> value = value.toList()
            }
            list.add(value)
        }
        return list
    }
}