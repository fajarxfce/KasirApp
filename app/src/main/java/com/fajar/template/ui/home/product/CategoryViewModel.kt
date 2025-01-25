package com.fajar.template.ui.home.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCase: CategoryUseCase) : ViewModel() {

    val categories = categoryUseCase.getCategories().asLiveData()
    val selectedCategory: List<Category> = emptyList()

    fun addCategory(
        category: Category,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            categoryUseCase.addCategory(category).collect { resource ->
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