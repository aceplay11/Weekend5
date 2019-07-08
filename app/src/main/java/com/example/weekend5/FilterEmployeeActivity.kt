package com.example.weekend5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_filter_employee.*

class FilterEmployeeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
        private var spinner : Spinner? = null

    private var listOfDepartments = arrayOf("Select Database","FrontEnd", "Backend", "HR", "Management")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_employee)

//        this.spinner!!.onItemSelectedListener
        spinner = this.depSpinner
        spinner?.onItemSelectedListener = this

        val departmentArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfDepartments )
        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = departmentArrayAdapter


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

        if (position == 0){

        }else {
            var passingString = listOfDepartments[position]
            Log.d("TAG", "Selected item $passingString")
            val intent = Intent(this, EmployeeListActivity::class.java)
            intent.putExtra("Selected", passingString)
            startActivity(intent)
        }
    }

    fun onClick(view: View)  =
        when (view.id) {
            R.id.btnFilterAct-> {
                startActivity(Intent(this, FilterEmployeeActivity::class.java))
            }
            else -> {
            }
        }

}
