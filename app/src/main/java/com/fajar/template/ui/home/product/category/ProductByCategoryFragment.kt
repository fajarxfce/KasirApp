package com.fajar.template.ui.home.product.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fajar.template.R
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.FragmentProductByCategoryBinding
import com.fajar.template.ui.home.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ProductByCategoryFragment : Fragment() {
    private val binding by lazy { FragmentProductByCategoryBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ProductViewModel>()
    private lateinit var data: Category
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data = arguments?.getParcelable<Category>("category") as Category

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

    companion object {
        private const val TAG = "ProductByCategoryFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductByCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}