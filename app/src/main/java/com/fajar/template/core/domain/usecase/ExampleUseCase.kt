package com.fajar.template.core.domain.usecase

import com.fajar.template.core.data.Resource
import com.fajar.template.core.model.Example
import kotlinx.coroutines.flow.Flow

interface ExampleUseCase {
    // fetch from API
    fun fetchExample(): Flow<Resource<Example>>

    // fetch from local
    fun getExample(): Flow<Resource<Example>>
}