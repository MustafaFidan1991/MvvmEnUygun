package com.enuygun.ui.main

import android.support.v7.app.AppCompatActivity
import android.view.View
import com.enuygun.R
import com.enuygun.base.BaseRecyclerAdapter
import com.enuygun.databinding.MainItemBinding
import com.enuygun.model.FlightItemWrapper
import com.enuygun.utils.Tools.toggleArrow
import com.enuygun.utils.ViewAnimation

class FlightItemAdapter(activity:AppCompatActivity,val onAnySelectBtnClicked: ()->Unit) : BaseRecyclerAdapter<FlightItemWrapper,FlightItemViewModel,MainItemBinding>(activity) {

    override fun onItemBinding(binding: MainItemBinding, viewModel: FlightItemViewModel, position: Int) {

        binding.expandBtn.setOnClickListener{
            view->
            toggleSection(view, binding.linearLayout3)
        }


        binding.selectBtn.setOnClickListener{
            onAnySelectBtnClicked()
        }



    }

    override fun getViewModel(): FlightItemViewModel {
        return FlightItemViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.main_item
    }

    private fun toggleSection(button: View, layout: View) {
        val show = toggleArrow(button)
        if (show) {
            ViewAnimation.expand(layout) { }
        } else {
            ViewAnimation.collapse(layout)
        }
    }

    private fun toggleArrow(view: View): Boolean {
        if (view.rotation == 0f) {
            view.animate().setDuration(200).rotation(180f)
            return true
        } else {
            view.animate().setDuration(200).rotation(0f)
            return false
        }
    }
}