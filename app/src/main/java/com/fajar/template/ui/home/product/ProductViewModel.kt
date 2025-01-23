package com.fajar.template.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {

    val products = productUseCase.getProducts().asLiveData()

    fun addProduct(product: Product) {
        viewModelScope.launch {
            productUseCase.addProduct(product).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Show loading state
                    }
                    is Resource.Success -> {
                        // Handle success
                    }
                    is Resource.Error -> {
                        // Show error message
                    }
                }
            }
        }
    }

}