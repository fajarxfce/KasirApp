package com.fajar.template.ui.home.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fajar.template.R
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ProductViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProducts(
            onLoading = {
                Log.d(TAG, "getProducts: Loading")
            },
            onSuccess = {
                it.forEach {
                    Log.d(TAG, "getProducts: ${it.name}")
                }
            },
            onError = {
                Log.d(TAG, "getProducts: $it")
            }
        )
        var no = 1
        binding.btnAddProduct.setOnClickListener {

            val product = Product(
                name = "Product $no",
                description = "Description 1",
                image = "https://via.placeholder.com/150",
                price = 10000.0,
                stock = 10
            )
            viewModel.addProduct(product,
                onLoading = {
                    Log.d(TAG, "addProduct: Loading")
                },
                onSuccess = {
                    Log.d(TAG, "addProduct: Success")
                    no++
                },
                onError = {
                    Log.d(TAG, "addProduct: $it")
                }
            )
        }


    }

    companion object {
        private const val TAG = "ProductFragment"
    }
}