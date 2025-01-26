package com.fajar.template.ui.home.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.template.R
import com.fajar.template.core.adapter.CategoryAdapter
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.FragmentCategoryBinding
import com.fajar.template.databinding.FragmentProductManagementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var adapter : CategoryAdapter

    private val binding by lazy {
        FragmentCategoryBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ProductViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryAdapter()

        categoryViewModel.categories.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    adapter.setListProduct(it.data ?: emptyList())
                    binding.recyclerView.adapter = adapter
                }
                is Resource.Error -> {
                    Log.d(TAG, "onViewCreated: Error")
                }
            }
        }

        adapter.setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Category) {
                viewModel.getProductByCategory(data.id!!).observe(viewLifecycleOwner) {
                    when(it){
                        is Resource.Loading -> {
                            Log.d(TAG, "onViewCreated: Loading")
                        }
                        is Resource.Success -> {
                            Log.d(TAG, "onViewCreated: Success")
                            it.data?.forEach {
                                Log.d(TAG, "onViewCreated: ${it.name}")
                            }
                        }
                        is Resource.Error -> {
                            Log.d(TAG, "onViewCreated: Error")
                        }
                    }
                }
            }
        })


        adapter.setOnItemLongClickCallback(object : CategoryAdapter.OnItemLongClickCallback {
            override fun onItemLongClicked(data: Category) {
                Log.d(TAG, "onItemLongClicked: ${data.name}")
            }
        })


        var no = 1
        binding.btnAddCategory.setOnClickListener {
            val category = Category(
                name = "Category $no"
            )
            categoryViewModel.addCategory(category, {
                Log.d(TAG, "onViewCreated: Loading")
            }, {
                Log.d(TAG, "onViewCreated: Success")
            }, {
                Log.d(TAG, "onViewCreated: Error")
            })
            no++
        }
    }

    companion object {
        private const val TAG = "CategoryFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}