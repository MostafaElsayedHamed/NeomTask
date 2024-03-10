package com.interview.mostafa.task.feature_home.data.remote.response

data class CoffeeItemDto(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val title: String
)