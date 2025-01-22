package com.fajar.template.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fajar.template.R
import com.fajar.template.core.adapter.MenuAdapter
import com.fajar.template.core.model.Menu
import com.fajar.template.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuTop = listOf(
            Menu("Product", R.drawable.product),
            Menu("Menu 2", R.drawable.product),
            Menu("Menu 3", R.drawable.product),
        )

        val adapter = MenuAdapter(requireContext(), menuTop)

        binding.menuTop.horizontalSpacing = 10
        binding.menuTop.verticalSpacing = 10
        binding.menuTop.adapter = adapter

        val adapter2 = MenuAdapter(requireContext(), menuTop)
        binding.menuBottom.horizontalSpacing = 10
        binding.menuBottom.verticalSpacing = 10
        binding.menuBottom.adapter = adapter2

        adapter.setOnMenuClickListener(object : MenuAdapter.OnMenuClickListener {
            override fun onClick(menu: Menu) {
                when (menu.name) {
                    "Product" -> {
                        findNavController().navigate(R.id.action_homeFragment_to_productFragment)
                    }
                    "Menu 2" -> {
                        Log.d(TAG, "onClick: Menu 2")
                    }
                    "Menu 3" -> {
                        Log.d(TAG, "onClick: Menu 3")
                    }
                }
            }
        })


    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}