package com.fajar.template.core.domain.repository

import com.fajar.template.core.data.Resource
import com.fajar.template.core.model.Example
import kotlinx.coroutines.flow.Flow

interface IExampleRespository {
    fun fetchExample(): Flow<Resource<Example>>
    fun getExample(): Flow<Resource<Example>>
}