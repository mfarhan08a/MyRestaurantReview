package com.example.myrestaurantreview.di

import com.example.myrestaurantreview.data.RestaurantRepository
import com.example.myrestaurantreview.data.network.ApiConfig

object Injection {
    fun provideRepository(): RestaurantRepository {
        val apiService = ApiConfig.getApiService()
        return RestaurantRepository.getInstance(apiService)
    }
}