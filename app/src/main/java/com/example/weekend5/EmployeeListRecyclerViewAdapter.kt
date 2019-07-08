package com.example.weekend5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.employee_item_recycler_layout.view.*

class EmployeeListRecyclerViewAdapter (var employeeDetails : ArrayList<Employee>, val context : Context)
    : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tvFirstName.text = employeeDetails.get(position).firstName
        holder.itemView.tvLastName.text = employeeDetails.get(position).lastName
        holder.itemView.tvAddress.text = employeeDetails.get(position).streetAddress
        holder.itemView.tvCity.text = employeeDetails.get(position).city
        holder.itemView.tvState.text = employeeDetails.get(position).state
        holder.itemView.tvTaxID.text = employeeDetails.get(position).taxID
        holder.itemView.tvPosition.text = employeeDetails.get(position).jobPosition
        holder.itemView.tvDepartment.text = employeeDetails.get(position).department

    }

    override fun getItemCount(): Int {
        return employeeDetails.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.employee_item_recycler_layout, parent, false))
    }



    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
//        val tvFirstName = view.tvFirstName
//        val tvLastName = view.tvLastName
//        val tvAddress = view.tvAddress
//        val tvCity = view.tvCity
//        val tvState = view.tvState
//        val tvZip = view.tvZip
//        val tvTaxID = view.tvTaxID
//        val tvPos = view.tvPosition
//        val tvDepot = view.tvDepartment

    }
}


