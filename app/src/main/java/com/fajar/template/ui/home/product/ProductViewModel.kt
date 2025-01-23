package com.fajar.template.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productUseCase: ProductUseCase) : ViewModel() {
    fun addProduct(product: Product, onResult: () -> Unit) {
        viewModelScope.launch {
            productUseCase.addProduct(product).collect {
                onResult()
            }
        }
    }

    fun getProducts(onResult: (List<Product>) -> Unit) {
        viewModelScope.launch {
            productUseCase.getProducts().collect {
                onResult(it)
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