package com.fajar.template.ui.home.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.template.R
import com.fajar.template.core.adapter.ProductAdapter
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.FragmentProductBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private val binding by lazy { FragmentProductBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ProductViewModel>()
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnAddProduct.setOnClickListener {
            val product = Product(
                null,
                "Product 1",
                "Description 1",
                "https://via.placeholder.com/150",
                1000.0,
                10
            )
            viewModel.addProduct(product,
                onLoading = {},
                onSuccess = {Snackbar.make(view, "Success", Snackbar.LENGTH_SHORT).show()},
                onError = {}
                )
        }
    }

    companion object {
        private const val TAG = "ProductFragment"
    }
}