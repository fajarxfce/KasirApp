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

        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter(emptyList())
        binding.rvProduct.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner) { results ->
            when (results) {
                is Resource.Loading -> {
                    Log.d(TAG, "anjay: Loading")
                }

                is Resource.Success -> {
                    results.data?.forEach {
                        Log.d(TAG, "anjay: ${it.name}")
                    }
                    adapter.updateData(results.data?: emptyList())
                    binding.rvProduct.adapter = adapter
                }

                is Resource.Error -> {
                    Log.d(TAG, "anjay: ${results.message}")
                }

            }
        }
    }
    companion object {
        private const val TAG = "ProductFragment"
    }
}