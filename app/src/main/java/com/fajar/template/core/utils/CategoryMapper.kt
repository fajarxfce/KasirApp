package com.fajar.template.core.utils

import com.fajar.template.core.data.source.local.entity.CategoryEntity

object CategoryMapper {
    fun categoriesEntityToDomain(entity: List<CategoryEntity>) =
        entity.map {
            CategoryEntity(
                it.categoryId,
                it.name
            )
        }

    fun categoryEntityToDomain(entity: CategoryEntity) =
        CategoryEntity(
            entity.categoryId,
            entity.name
        )

    fun categoryDomainToEntity(domain: CategoryEntity) =
        CategoryEntity(
            domain.categoryId,
            domain.name
        )

    fun categoriesDomainToEntity(domain: List<CategoryEntity>) =
        domain.map {
            CategoryEntity(
                it.categoryId,
                it.name
            )
        }
}