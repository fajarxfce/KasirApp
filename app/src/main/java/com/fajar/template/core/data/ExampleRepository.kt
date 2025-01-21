package com.fajar.template.core.data

import com.fajar.template.core.data.source.local.LocalDataSource
import com.fajar.template.core.data.source.remote.RemoteDataSource
import com.fajar.template.core.data.source.remote.network.ApiResponse
import com.fajar.template.core.data.source.remote.response.ExampleResponse
import com.fajar.template.core.domain.repository.IExampleRespository
import com.fajar.template.core.model.Example
import com.fajar.template.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): IExampleRespository {
    companion object {
        private val TAG = ExampleRepository::class.java.simpleName
    }

    override fun fetchExample(): Flow<Resource<Example>> {
        return object : NetworkBoundResource<Example, ExampleResponse>() {
            override fun loadFromDB(): Flow<Example> {
                return localDataSource.getExampleList().map {
                    DataMapper.exampleEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Example?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<ExampleResponse>> {
                return remoteDataSource.getExample()
            }

            override suspend fun saveCallResult(data: ExampleResponse) {
                val example = DataMapper.exampleDomainToEntity(data)
                localDataSource.insertExample(example)
            }
        }.asFlow()
    }

    override fun getExample(): Flow<Resource<Example>> {
        TODO("Not yet implemented")
    }


}