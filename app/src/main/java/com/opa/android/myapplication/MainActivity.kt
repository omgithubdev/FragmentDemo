package com.opa.android.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.opa.android.myapplication.adapter.MyPagerAdapter
import com.opa.android.myapplication.databinding.ActivityMainBinding
import com.opa.android.myapplication.fragment.FragmentDetails
import com.opa.android.myapplication.fragment.FragmentForm

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
        setObservers()
    }

    private fun setObservers() {
        viewModel.datailsLiveData.observe(this, Observer {
            it?.let {
                binding.viewPager.setCurrentItem(1)
            }
        })
    }

    private fun initViews() {
        binding.viewPager.adapter = MyPagerAdapter(supportFragmentManager)
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 1) {
            binding.viewPager.setCurrentItem(0)
            return
        }
        super.onBackPressed()
    }


}