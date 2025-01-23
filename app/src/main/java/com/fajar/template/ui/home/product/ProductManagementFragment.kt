package com.fajar.template.ui.home.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fajar.template.R
import com.fajar.template.core.adapter.ViewPagerAdapter
import com.fajar.template.databinding.FragmentProductManagementBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductManagementFragment : Fragment() {

    private val binding by lazy {
        FragmentProductManagementBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ProductViewModel>()
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

    }
}