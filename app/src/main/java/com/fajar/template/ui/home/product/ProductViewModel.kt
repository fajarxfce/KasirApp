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

}