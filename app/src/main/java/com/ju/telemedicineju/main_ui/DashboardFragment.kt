package com.ju.telemedicineju.main_ui

import androidx.navigation.NavController
import com.ju.telemedicineju.BaseOnBoardFragment
import com.ju.telemedicineju.R
import com.ju.telemedicineju.bottomNavSet
import com.ju.telemedicineju.databinding.FragmentDashnboardBinding
import com.ju.telemedicineju.navControllerProvider

class DashboardFragment : BaseOnBoardFragment<FragmentDashnboardBinding>() {


    override fun getFragmentView(): Int {
        return R.layout.fragment_dashnboard
    }


    override fun configUi() {

    }

}