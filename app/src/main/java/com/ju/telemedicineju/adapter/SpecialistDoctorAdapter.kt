package com.ju.telemedicineju.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ju.telemedicineju.R
import com.ju.telemedicineju.model.input.Doctor
import com.ju.telemedicineju.viewHolder.SpecialistDoctorViewHolder

class SpecialistDoctorAdapter(private val list: List<Doctor>, val context: Context) : RecyclerView.Adapter<SpecialistDoctorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialistDoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specilit, parent,false)
        return SpecialistDoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialistDoctorViewHolder, position: Int) {

        val model = list[position]
        holder.name.text = model.Full_Name
        Glide.with(context).load(model.Profile_Photo).placeholder(R.drawable.ic_doctor).into(holder.profile)
    }
    override fun getItemCount(): Int {
       return list.size
    }


}