package com.fajar.template.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.template.ui.home.product.CategoryFragment
import com.fajar.template.ui.home.product.ProductFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragment = listOf(
        ProductFragment(),
        CategoryFragment()
    )

    private val title = listOf(
        "Product",
        "Category"
    )

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment = fragment[position]

    fun getPageTitle(position: Int): CharSequence = title[position]
}