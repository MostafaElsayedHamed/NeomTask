package com.interview.mostafa.task.core.utli

import android.content.Context
import android.widget.Toast
import com.interview.mostafa.task.core.UiText
import com.interview.mostafa.task.core.asString

fun Context.toast(uiText: UiText) {
    Toast.makeText(this, uiText.asString(this), Toast.LENGTH_SHORT).show()
}