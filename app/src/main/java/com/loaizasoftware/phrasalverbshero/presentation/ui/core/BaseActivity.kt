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

}