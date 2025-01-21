package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.repository.IExampleRespository
import com.fajar.template.core.model.Example
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExampleInteractor @Inject constructor(private val repository: IExampleRespository): ExampleUseCase {
    override fun fetchExample(): Flow<Resource<Example>> {
        return repository.fetchExample()
    }

    override fun getExample(): Flow<Resource<Example>> {
        return repository.getExample()
    }
}