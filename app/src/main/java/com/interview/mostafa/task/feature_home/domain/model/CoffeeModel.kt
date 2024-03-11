package com.interview.mostafa.task.feature_home.domain.model

import com.interview.mostafa.task.feature_home.data.remote.response.CoffeeItemDto
import org.json.JSONArray

data class CoffeeModel(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: String,
    val title: String
)

fun CoffeeItemDto.toCoffeeModel(): CoffeeModel {
    return CoffeeModel(
        description = description,
        id = id,
        image = image,
        ingredients = JSONArray(ingredients).toString(),
        title = title
    )
}