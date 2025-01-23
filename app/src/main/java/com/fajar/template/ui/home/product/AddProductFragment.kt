package com.fajar.template.ui.home.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fajar.template.R
import com.fajar.template.databinding.FragmentAddProductBinding

class AddProductFragment : Fragment() {

    private val binding by lazy {
        FragmentAddProductBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ProductViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}