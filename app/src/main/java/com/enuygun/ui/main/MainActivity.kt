package com.enuygun.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.enuygun.R
import com.enuygun.base.BaseActivity
import com.enuygun.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        binding.adapter = FlightItemAdapter(this){
            viewModel.getReturnedFlights()
        }
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        viewModel.getFlights()

        viewModel.flightItemWrapperListLiveData.observe(this, Observer {
            list->
            if(list!=null){
                binding.adapter!!.update(list)
            }

        })

        viewModel.title.observe(this, Observer {
            title->
            binding.titleTv.text = title
        })

        viewModel.originDestinationCode.observe(this, Observer {
            code->
            binding.originDestinationTv.text = code
        })


        viewModel.selectedDate.observe(this, Observer {
            date->
            binding.selectedDateTv.text = date
        })

    }

}
