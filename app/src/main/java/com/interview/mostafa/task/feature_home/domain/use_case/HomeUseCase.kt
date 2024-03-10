package com.interview.mostafa.task.feature_home.domain.use_case

import com.interview.mostafa.task.feature_home.domain.model.CoffeeModel
import com.interview.mostafa.task.feature_home.data.repository.HomeRepository
import com.interview.mostafa.task.core.Resource

class HomeUseCase(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(): Resource<List<CoffeeModel>> {
        return repository.getCoffeeList()
    }
}