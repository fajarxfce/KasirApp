package com.fajar.template.ui.home.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fajar.template.R
import com.fajar.template.core.domain.model.Category
import com.fajar.template.core.domain.model.Product
import com.fajar.template.databinding.FragmentAddProductBinding
import com.fajar.template.helper.FormFieldText
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private val binding by lazy {
        FragmentAddProductBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ProductViewModel>()
    private var selectedCategories: List<Category> = emptyList()
    private val fieldName by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.name,
            textInputEditText = binding.etName,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> getString(R.string.error_product_name_empty)
                    else -> null
                }
            }
        )
    }

    private val fieldSellPrice by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.sellPrice,
            textInputEditText = binding.etSellPrice,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> getString(R.string.error_product_sell_price_empty)
                    else -> null
                }
            }
        )
    }

    private val fieldPurchasePrice by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.purchasePrice,
            textInputEditText = binding.etPurchasePrice,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> getString(R.string.error_product_purchase_price_empty)
                    else -> null
                }
            }
        )
    }

    private val fieldStock by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.stock,
            textInputEditText = binding.etStock,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> getString(R.string.error_product_stock_empty)
                    else -> null
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddProduct.setOnClickListener { submit() }
        binding.btnCategory.setOnClickListener {
            // show bottomsheet category dialog
            val dialog = CategoryBottomSheelFragment { categories ->
                selectedCategories = categories
                binding.etCategory.setText(categories.joinToString { it.name })
            }
            dialog.show(childFragmentManager, "CategoryBottomSheet")
        }
    }

    private fun submit() = lifecycleScope.launch {
        binding.btnAddProduct.isEnabled = false

        fieldName.disable()
        if (fieldName.validate() && fieldSellPrice.validate() && fieldPurchasePrice.validate() && fieldStock.validate()) {
            fieldName.value
            fieldSellPrice.value
            fieldPurchasePrice.value
            fieldStock.value
            viewModel.addProduct(
                Product(
                    name = fieldName.value ?: "",
                    sellPrice = fieldSellPrice.value?.toLong() ?: 0,
                    purchasePrice = fieldPurchasePrice.value?.toLong() ?: 0,
                    stock = fieldStock.value?.toInt() ?: 0,
                    image = "",
                    description = "",
                    barcode = ""
                ),
                onLoading = {},
                onSuccess = {
                    Log.d(TAG, "submit: ${it}")
                    showToast(getString(R.string.success_add_product, fieldName.value))
                    requireActivity().onBackPressed()
                },
                onError = {
                    showToast(getString(R.string.failed_add_product, fieldName))
                }
            )
            showToast(getString(R.string.success_add_product, fieldName.value))
        }

        binding.btnAddProduct.isEnabled = true
        fieldName.enable()
        fieldSellPrice.enable()
        fieldPurchasePrice.enable()
        fieldStock.enable()
    }

    private fun showToast(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "AddProductFragment"
    }
}