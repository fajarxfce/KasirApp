package com.fajar.template.ui.home.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fajar.template.R
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.FragmentAddProductBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        binding.btnAddProduct.setOnClickListener {
            val name = binding.etName.text.toString()
            val sellPrice = binding.etSellPrice.text.toString().toLong()
            val purchasePrice = binding.etPurchasePrice.text.toString().toLong()
            val stock = binding.etStock.text.toString().toInt()
            val barcode = binding.etBarcode.text.toString()

            val product = Product(
                name = name,
                image = "",
                stock = stock,
                description = "",
                sellPrice = sellPrice,
                purchasePrice = purchasePrice,
                barcode = barcode
            )
            viewModel.addProduct(product, {
                // onLoading
            }, {
                // onSuccess
                Snackbar.make(view, "$name Berhasil Ditambahkan", Snackbar.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }, {
                // onError
                Snackbar.make(view, "Gagal Menambahkan $name", Snackbar.LENGTH_SHORT).show()
            })
        }
    }
}