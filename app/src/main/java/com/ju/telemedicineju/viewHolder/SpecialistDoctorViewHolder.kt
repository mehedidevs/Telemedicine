package com.ju.telemedicineju.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ju.telemedicineju.R

class SpecialistDoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val profile: ImageView = view.findViewById(R.id.profileImage)
    val name: TextView = view.findViewById(R.id.doctorName)
}