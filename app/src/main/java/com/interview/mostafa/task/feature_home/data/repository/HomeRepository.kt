package com.interview.mostafa.task.feature_home.data.repository

import com.interview.mostafa.task.core.Resource
import com.interview.mostafa.task.feature_home.domain.model.CoffeeModel

interface HomeRepository {
    suspend fun getCoffeeList(): Resource<List<CoffeeModel>>
}