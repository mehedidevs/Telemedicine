package com.ju.telemedicineju.main_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ju.telemedicineju.BaseOnBoardFragment

import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentAppoinmentBinding


class AppoinmentFragment : BaseOnBoardFragment<FragmentAppoinmentBinding>() {


    override fun getFragmentView(): Int {
        return R.layout.fragment_appoinment
    }


}