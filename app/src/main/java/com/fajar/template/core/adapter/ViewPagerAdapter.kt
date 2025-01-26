package com.fajar.template.core.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.template.ui.home.product.CategoryFragment
import com.fajar.template.ui.home.product.ProductFragment
import com.fajar.template.ui.home.product.ProductManagementFragment

class ViewPagerAdapter(fragment: ProductManagementFragment) : FragmentStateAdapter(fragment) {

    private val fragment = listOf(
        ProductFragment.newInstance(),
        CategoryFragment.newInstance("","")
    )

    private val title = listOf(
        "Product",
        "Category"
    )

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return itemId in 0 until itemCount
    }
    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment = fragment[position]

    fun getPageTitle(position: Int): CharSequence = title[position]
}