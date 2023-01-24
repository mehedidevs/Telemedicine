package com.ju.telemedicineju

import android.R
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ContextUtils.getActivity
import com.ju.telemedicineju.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val frg = binding.fragmentContainerView.getFragment<Fragment>()
//
//        val f:Fragment= get.findFragmentById(R.id.fragmentContainerView)!!
//
//        if (frg.is) {
//            binding.bottomNav.visibility = View.VISIBLE
//
//        } else {
//            binding.bottomNav.visibility = View.GONE
//        }




    }
}