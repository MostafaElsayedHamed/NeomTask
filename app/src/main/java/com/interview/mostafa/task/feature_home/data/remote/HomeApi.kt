package com.interview.mostafa.task.feature_home.data.remote

import com.interview.mostafa.task.feature_home.data.remote.response.CoffeeItemDto
import retrofit2.http.GET

interface HomeApi {

    @GET("hot")
    suspend fun coffeeList(): List<CoffeeItemDto>
}