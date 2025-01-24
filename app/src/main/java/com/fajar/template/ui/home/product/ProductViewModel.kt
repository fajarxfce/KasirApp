package com.fajar.template.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import com.fajar.template.core.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productUseCase: ProductUseCase) :
    ViewModel() {

    val products = productUseCase.getProducts().asLiveData()

    fun addProduct(
        product: Product,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            productUseCase.addProduct(product).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        onLoading()
                    }

                    is Resource.Success -> {
                        onSuccess()
                    }

                    is Resource.Error -> {
                        onError()
                    }
                }
            }
        }
    }

    fun deleteProduct(
        id: Int,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            productUseCase.deleteProduct(id).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        onLoading()
                    }

                    is Resource.Success -> {
                        onSuccess()
                    }

                    is Resource.Error -> {
                        onError()
                    }
                }
            }
        }
    }

}