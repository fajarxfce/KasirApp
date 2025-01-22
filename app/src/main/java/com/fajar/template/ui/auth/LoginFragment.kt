package com.fajar.template.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fajar.template.R
import com.fajar.template.core.data.Resource
import com.fajar.template.core.domain.model.User
import com.fajar.template.core.domain.usecase.AuthUseCase
import com.fajar.template.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(
                email,
                password
            ){
                when(it){
                    is Resource.Success -> {
                        Log.d(TAG, "onViewCreated: ${it.data?.name}")
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "onViewCreated: ${it.message}")
                    }

                    else -> {
                        Log.d(TAG, "onViewCreated: unknown error")
                    }
                }
            }
        }
        var id = 4
        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val user = User(null, "Fajar", email, password)
            viewModel.registerUser(user){
                Log.d(TAG, "onViewCreated: $it")
            }
            id++
        }


    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}