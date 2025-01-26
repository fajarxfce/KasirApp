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
import com.fajar.template.helper.disableALl
import com.fajar.template.helper.enableAll
import com.fajar.template.helper.validateAll
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.android.view.clicks

@AndroidEntryPoint
class AddProductFragment : Fragment() {

    private val binding by lazy {
        FragmentAddProductBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<ProductViewModel>()
    private val categoryViewModel by viewModels<CategoryViewModel>()
    private var selectedCategories: List<Category> = emptyList()

    private var product: Product? = null

    private val fieldName by lazy {
        FormFieldText(
            scope = lifecycleScope,
            textInputLayout = binding.name,
            textInputEditText = binding.etName,
            validation = { value ->
                when {
                    value.isNullOrBlank() -> getString(R.string.error_product_name_empty)
                    value.isEmpty() -> getString(R.string.error_product_name_empty)
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
                    value.isEmpty() -> getString(R.string.error_product_sell_price_empty)
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
                    value.isEmpty() -> getString(R.string.error_product_purchase_price_empty)
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
                    value.isEmpty() -> getString(R.string.error_product_stock_empty)
                    else -> null
                }
            }
        )
    }

    private val formFields by lazy {
        listOf(fieldName, fieldSellPrice, fieldPurchasePrice, fieldStock)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product = arguments?.getParcelable("product")
        if (product != null) {
            //set action bar title
            requireActivity().title = getString(R.string.update_product)
            binding.etName.setText(product?.name)
            binding.etSellPrice.setText(product?.sellPrice.toString())
            binding.etPurchasePrice.setText(product?.purchasePrice.toString())
            binding.etStock.setText(product?.stock.toString())
            binding.etBarcode.setText(product?.barcode)
            binding.btnAddProduct.text = getString(R.string.update)
        } else {
            requireActivity().title = getString(R.string.add_product)
            binding.btnAddProduct.text = getString(R.string.add)

            //dummy data
            binding.etName.setText("Product 1")
            binding.etSellPrice.setText("10000")
            binding.etPurchasePrice.setText("8000")
            binding.etStock.setText("10")
            binding.etBarcode.setText("1234567890")

        }

        binding.btnAddProduct.clicks().onEach { submit() }.launchIn(lifecycleScope)
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
        formFields.disableALl()
        if (formFields.validateAll()) {
            if (product != null) {
                updateProduct()
            } else {
                addProduct()
            }
        }
        formFields.enableAll()
    }

    private fun updateProduct() {
        viewModel.updateProduct(
            Product(
                id = product?.id!!,
                name = fieldName.value ?: "",
                sellPrice = fieldSellPrice.value?.toLong() ?: 0,
                purchasePrice = fieldPurchasePrice.value?.toLong() ?: 0,
                stock = fieldStock.value?.toInt() ?: 0,
                image = "",
                description = "",
                barcode = ""
            ),
            selectedCategories,
            onLoading = {},
            onSuccess = {

                selectedCategories.forEach { category ->
                    viewModel.addProductCategoryCrossRef(
                        productId = product?.id!!,
                        categoryId = category.id!!,
                        onLoading = {},
                        onSuccess = {},
                        onError = {}
                    )
                }
                showToast(getString(R.string.success_add_product, fieldName.value))
                requireActivity().onBackPressed()
            },
            onError = {
                showToast(getString(R.string.failed_add_product, fieldName.value))
            }
        )
    }

    private fun addProduct() {
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
            selectedCategories,
            onLoading = {},
            onSuccess = {
                Log.d(TAG, "submit: ${it}")
                selectedCategories.forEach { category ->
                    viewModel.addProductCategoryCrossRef(
                        it.toInt(),
                        category.id!!,
                        onLoading = {},
                        onSuccess = {},
                        onError = {}
                    )
                }
                showToast(getString(R.string.success_add_product, fieldName.value))
                requireActivity().onBackPressed()
            },
            onError = {
                showToast(getString(R.string.failed_add_product, fieldName.value))
            }
        )
    }

    private fun showToast(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "AddProductFragment"
    }
}