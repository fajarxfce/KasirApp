package com.fajar.template.core.utils

import com.fajar.template.core.data.source.local.entity.ExampleEntity
import com.fajar.template.core.data.source.remote.response.ExampleResponse
import com.fajar.template.core.model.Example

object DataMapper {
    fun exampleDomainToEntity(input: ExampleResponse): ExampleEntity =
        ExampleEntity(
            id = input.data.id,
            name = input.data.name,
            description = input.data.description
        )

    fun exampleEntityToDomain(input: ExampleEntity): Example =
        Example(
            id = input.id,
            name = input.name,
            description = input.description
        )
}