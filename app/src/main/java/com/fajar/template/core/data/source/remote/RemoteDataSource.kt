package com.fajar.template.core.data.source.remote

import com.fajar.template.core.data.source.remote.network.ApiResponse
import com.fajar.template.core.data.source.remote.network.ApiService
import com.fajar.template.core.data.source.remote.response.ExampleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName
    }

    suspend fun getExample(): Flow<ApiResponse<ExampleResponse>> {
        return flow {
            try {
                val response = apiService.getExample()
                if (response.code == 200) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

}