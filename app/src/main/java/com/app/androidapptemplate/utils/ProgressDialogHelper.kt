package com.app.androidapptemplate.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.assignment.androidapptemplate.R

class ProgressDialogHelper(private val context: Context) {

    private var progressDialog: Dialog? = null

    fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = Dialog(context).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(false)
                setContentView(R.layout.dialog_progress)
            }
        }
        if (progressDialog?.isShowing == false) {
            progressDialog?.show()
        }
    }

    fun hideProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}
