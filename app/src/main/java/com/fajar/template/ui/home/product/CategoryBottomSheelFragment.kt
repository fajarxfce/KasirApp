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
import com.fajar.template.core.adapter.CategoryBottomsheetAdapter
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.Category
import com.fajar.template.databinding.FragmentCategoryBottomSheelBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryBottomSheelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CategoryBottomSheelFragment(private val oncategorySelected: (List<Category>) -> Unit) : BottomSheetDialogFragment() {
    private val binding by lazy { FragmentCategoryBottomSheelBinding.inflate(layoutInflater) }
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var adapter: CategoryBottomsheetAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CategoryBottomsheetAdapter(emptyList())
        viewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "onViewCreated: Success")
                    adapter = CategoryBottomsheetAdapter(it.data ?: emptyList())
                    binding.recyclerView.adapter = adapter
                }
                is Resource.Error -> {
                    Log.d(TAG, "onViewCreated: Error")
                }
            }

            binding.btnSelect.setOnClickListener {
                val selectedCategories = adapter.getSelectedCategories()
                oncategorySelected(selectedCategories)
                dismiss()
            }
        }
    }

    companion object {
        private const val TAG = "CategoryBottomSheelFragment"
    }
}