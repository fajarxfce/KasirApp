package com.fajar.template.core.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.fajar.template.R
import com.fajar.template.core.model.Menu

class MenuAdapter(private val context: Context, private val items: List<Menu>) : BaseAdapter() {

    interface OnMenuClickListener {
        fun onClick(menu: Menu)
    }

    private var onMenuClickListener: OnMenuClickListener? = null

    fun setOnMenuClickListener(onMenuClickListener: OnMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener
    }

    override fun getCount(): Int  = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false)
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = items.get(position).name

        view.setOnClickListener {
            onMenuClickListener?.onClick(items.get(position))
        }

        return view
    }

}