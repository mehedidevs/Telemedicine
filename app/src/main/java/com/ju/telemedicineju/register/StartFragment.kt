package com.ju.telemedicineju.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ju.telemedicineju.BaseOnBoardFragment
import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentStartBinding

class StartFragment : BaseOnBoardFragment<FragmentStartBinding>() {

    override fun getFragmentView(): Int {
        return R.layout.fragment_start
    }


    override fun configUi() {

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_homeFragment)
        }

    }


}