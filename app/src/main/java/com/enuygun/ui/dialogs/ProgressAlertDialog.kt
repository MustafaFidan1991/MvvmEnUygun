package com.enuygun.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import com.enuygun.R
import com.enuygun.base.BaseDialog
import com.enuygun.databinding.ProgressAlertDialogBinding

class ProgressAlertDialog(activity:AppCompatActivity) : BaseDialog<ProgressAlertViewModel,ProgressAlertDialogBinding>(activity) {
    init {
        setupDialog()
    }
    override fun setupDialog() {


        alertDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.setCancelable(false)

    }

    override fun instantiateViewModel(): ProgressAlertViewModel {
        return ProgressAlertViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.progress_alert_dialog
    }
}