package com.fajar.template

import com.fajar.template.core.data.ProductRepository
import com.fajar.template.core.data.source.local.ProductDataSource
import com.fajar.template.core.data.source.local.entity.ProductEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class ProductRepositoryTest {
    @Mock
    private lateinit var productDataSource: ProductDataSource

    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        productRepository = ProductRepository(productDataSource)
    }
}