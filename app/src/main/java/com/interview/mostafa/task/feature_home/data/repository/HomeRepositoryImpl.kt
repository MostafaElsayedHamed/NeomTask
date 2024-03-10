package com.interview.mostafa.task.feature_home.data.repository

import com.interview.mostafa.task.feature_home.domain.model.CoffeeModel
import com.interview.mostafa.task.R
import com.interview.mostafa.task.core.Resource
import com.interview.mostafa.task.core.UiText
import com.interview.mostafa.task.feature_home.data.remote.HomeApi
import com.interview.mostafa.task.feature_home.domain.model.toCoffeeModel
import java.io.IOException

class HomeRepositoryImpl(
    private val homeApi: HomeApi,
) : HomeRepository {


    override suspend fun getCoffeeList(): Resource<List<CoffeeModel>> {
        return try {
            val response = homeApi.coffeeList()
            Resource.Success(response.map { it.toCoffeeModel() })
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}