package com.example.myrestaurantreview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myrestaurantreview.data.model.PostReviewResponse
import com.example.myrestaurantreview.data.model.RestaurantDetailResponse
import com.example.myrestaurantreview.data.model.RestaurantResponse
import com.example.myrestaurantreview.data.network.ApiService
import kotlinx.coroutines.Dispatchers

class RestaurantRepository private constructor(
    private val apiService: ApiService
) {
    fun getRestaurantList(): LiveData<Result<RestaurantResponse>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.getRestaurantList()
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getRestaurantDetail(id: String): LiveData<Result<RestaurantDetailResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.getRestaurantDetail(id)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun postReview(id: String, name: String, review: String): LiveData<Result<PostReviewResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.postReview(id, name, review)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): RestaurantRepository = instance ?: synchronized(this) {
            instance ?: RestaurantRepository(apiService)
        }.also { instance = it }
    }
}