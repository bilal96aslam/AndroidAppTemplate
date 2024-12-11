package com.app.androidapptemplate.ui.login

import android.os.Bundle
import android.view.View
import com.app.androidapptemplate.base.BaseFragment
import com.app.androidapptemplate.utils.extension.showToast
import com.assignment.androidapptemplate.R
import com.assignment.androidapptemplate.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>(
    R.layout.fragment_login
) {

    override fun getViewModelClass() = LoginVM::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        onClick()
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            viewModel.login {
                showToast("login success")
            }
        }
    }
}