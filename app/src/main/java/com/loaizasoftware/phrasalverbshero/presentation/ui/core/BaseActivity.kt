package com.loaizasoftware.phrasalverbshero.presentation.ui.core

import android.app.AlertDialog
import androidx.activity.ComponentActivity
import com.loaizasoftware.phrasalverbshero.R

open class BaseActivity : ComponentActivity() {

    private var alertDialog: AlertDialog? = null

    protected fun showError(errorThrowable: Throwable) {

        alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage("${getString(R.string.error_description)} \n\n ${errorThrowable.message}")
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        alertDialog?.show()

    }

    protected fun showError(error: String) {

        alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.error)
            .setMessage("${getString(R.string.error_description)} \n\n $error")
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        alertDialog?.show()

    }


    override fun onResume() {
        super.onResume()
        instance = this
    }

    companion object {
        private var instance: BaseActivity? = null
        fun getInstance(): BaseActivity {
            if(instance == null) instance = BaseActivity()
            return instance as BaseActivity
        }
    }

}