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
    fun addProduct(product: Product, onLoading: () -> Unit, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            productUseCase.addProduct(product).collect { resource ->
                when (resource) {
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> onSuccess()
                    is Resource.Error -> onError("Error: ${resource.message}")
                }
            }
        }
    }

    fun getProducts(onLoading: () -> Unit, onSuccess: (List<Product>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            productUseCase.getProducts().collect {
                when (it) {
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> onSuccess(it.data?: emptyList())
                    is Resource.Error -> onError("Error: ${it.message}")
                }
            }
        }
    }

    fun getProduct(id: Int, onResult: (Product) -> Unit) {
        viewModelScope.launch {
            productUseCase.getProduct(id).collect {
                onResult(it)
            }
        }
    }

    fun updateProduct(product: Product, onResult: () -> Unit) {
        viewModelScope.launch {
            productUseCase.updateProduct(product).collect {
                onResult()
            }
        }
    }

    fun deleteProduct(id: Int, onResult: () -> Unit) {
        viewModelScope.launch {
            productUseCase.deleteProduct(id).collect {
                onResult()
            }
        }
    }

}