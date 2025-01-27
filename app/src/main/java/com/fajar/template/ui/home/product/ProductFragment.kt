package com.fajar.template.ui.home.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.fajar.template.R
import com.fajar.template.core.adapter.ProductAdapter
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.FragmentProductBinding
import com.fajar.template.helper.SpacingDecorator
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

        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter()
        val itemDecoration = SpacingDecorator.vertical(16)
        binding.rvProduct.addItemDecoration(itemDecoration)

        viewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    adapter.setListProduct(it.data ?: emptyList())
                    binding.rvProduct.adapter = adapter
                }
                is Resource.Error -> {
                    Log.d(TAG, "onViewCreated: Error")
                }
            }
        }

        binding.btnAddProduct.setOnClickListener {
            val action = ProductManagementFragmentDirections.actionProductManagementFragmentToAddProductFragment4()
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigate(action)
        }

        adapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Product) {
                val arg = Bundle()
                arg.putParcelable("product", data)
                val action = ProductManagementFragmentDirections.actionProductManagementFragmentToAddProductFragment4(data)
                findNavController().navigate(action)
            }
        })

        adapter.setOnItemLongClickCallback(object : ProductAdapter.OnItemLongClickCallback {
            override fun onItemLongClicked(data: Product) {
                deleteProduct(data.id!!)
            }
        })
    }

    private fun showDialog() {
        val newFragment = AddProductDialogFragment()
        newFragment.show(childFragmentManager, "ADD_PRODUCT")
    }

    fun deleteProduct(id: Int) {
        lifecycleScope.launch {
            viewModel.deleteProduct(id,
                onLoading = {},
                onSuccess = {Snackbar.make(requireView(), "Success", Snackbar.LENGTH_SHORT).show() },
                onError = {}
            )
        }
    }

    companion object {

        fun newInstance() = ProductFragment()

        private const val TAG = "ProductFragment"

    }
}