package com.ju.telemedicineju.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf

import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ju.telemedicineju.R
import com.ju.telemedicineju.model.input.Doctor
import de.hdodenhof.circleimageview.CircleImageView

class DoctorsAdapter (private val list: List<Doctor>, val context: Context) : RecyclerView.Adapter<DoctorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent,false)
        return DoctorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        val model = list[position]
        holder.name.text = model.Full_Name
        holder.spelizationTv.text = model.Specialization
        holder.adressTv.text = model.Chamber_Location_Map
        holder.feeTv.text = model.Fee+" tk"
        Glide.with(context).load(model.Profile_Photo).placeholder(R.drawable.ic_doctor).into(holder.profile)
        holder.appointmentBtn.setOnClickListener {
            val id = bundleOf("id" to model.Doctor_ID)
            it.findNavController().navigate(R.id.action_doctorsFragment_to_doctorsDetailsFragment, id)

        }
    }
    override fun getItemCount(): Int {
        return list.size
    }


}
class DoctorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val profile: CircleImageView = view.findViewById(R.id.profile_image)
    val name: TextView = view.findViewById(R.id.nameTv)
    val spelizationTv: TextView = view.findViewById(R.id.spelizationTv)
    val adressTv: TextView = view.findViewById(R.id.adressTv)
    val feeTv: TextView = view.findViewById(R.id.feeTv)
    val appointmentBtn: AppCompatButton = view.findViewById(R.id.appointmentBtn)

}