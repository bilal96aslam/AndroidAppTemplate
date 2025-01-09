package com.app.androidapptemplate.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.app.androidapptemplate.utils.ProgressDialogHelper

abstract class BaseFragment<VB : ViewBinding,VM : ViewModel>(
    @LayoutRes val contentLayoutId: Int
) : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: VB
    // Bind ViewModel (this can be overridden in child fragments if needed)
    protected abstract fun getViewModelClass(): Class<VM>
    private var progressDialogHelper: ProgressDialogHelper? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        progressDialogHelper = ProgressDialogHelper(requireContext())
        observeToastMessage()
        observeLoadingState()
    }

    // Function to observe toast message
    private fun observeToastMessage() {
        if (viewModel is BaseViewModel) {
            (viewModel as BaseViewModel).toastMessage.observe(viewLifecycleOwner) { message ->
                showToast(message)
            }
        }
    }

    // Function to observe loading state
    private fun observeLoadingState() {
        if (viewModel is BaseViewModel) {
            (viewModel as BaseViewModel).isLoading.observe(viewLifecycleOwner) { isLoading ->
                if (isLoading) {
                    progressDialogHelper?.showProgressDialog()
                } else {
                    progressDialogHelper?.hideProgressDialog()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        progressDialogHelper?.hideProgressDialog()
    }
}
