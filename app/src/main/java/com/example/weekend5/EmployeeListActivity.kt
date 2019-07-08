package com.example.weekend5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weekend5.fragments.AddNewEmployeeFragment
import com.example.weekend5.fragments.DeleteEmployeeFragment
import com.example.weekend5.fragments.UpdateEmployeeFragment
import com.example.weekend5.model.sqlite.EmployeeDBHelper
import kotlinx.android.synthetic.main.content_employee_list.*

class EmployeeListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    AddNewEmployeeFragment.OnFragmentInteractionListener, UpdateEmployeeFragment.OnFragmentInteractionListener,
    DeleteEmployeeFragment.OnFragmentInteractionListener{

    var employeeDBHelper = EmployeeDBHelper(this, null)
    lateinit var employeeDetailsForList : ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        var passedString = intent.getStringExtra("Selected")

        Log.d("TAG", "Select: $passedString")
        employeeDetailsForList = employeeDBHelper.returnDepartmentEmployees(passedString)

        rvEmployeeDetail.layoutManager = LinearLayoutManager(this)
        rvEmployeeDetail.adapter = EmployeeListRecyclerViewAdapter(employeeDetailsForList, this)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_Add_Employee -> {
                val newEntryFragment = AddNewEmployeeFragment.newInstance("", "")
                supportFragmentManager.beginTransaction().replace(R.id.fgFragments, newEntryFragment).addToBackStack("ADD").commit()

            }
            R.id.nav_Update_Employee -> {
                val updateEntryFragment = UpdateEmployeeFragment.newInstance("", "")
                supportFragmentManager.beginTransaction().replace(R.id.fgFragments, updateEntryFragment).addToBackStack("UPDATE").commit()

            }
            R.id.nav_Delete_Employee -> {
                val deleteEntryFragment = DeleteEmployeeFragment.newInstance("", "")
                supportFragmentManager.beginTransaction().replace(R.id.fgFragments, deleteEntryFragment).addToBackStack("DELETE").commit()
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun passedEmployeeObject(employee: Employee) {
        employeeDBHelper.insertNewEmployee(employee)
        Toast.makeText(this, "Added: ${employee.firstName} ${employee.lastName} in Department: ${employee.department} ", Toast.LENGTH_SHORT).show()
    }

    override fun updateEmployeeObject(taxID: String, passedTaxemployee: Employee) {
        employeeDBHelper.updateEmployee(taxID, passedTaxemployee)
        Toast.makeText(this, "Updated: ${passedTaxemployee.firstName} ${passedTaxemployee.lastName} in Department: ${passedTaxemployee.department} ", Toast.LENGTH_SHORT).show()
    }

    override fun passedEmployeeTaxID(passedEmployeeTaxID: String) {
        employeeDBHelper.deleteEmployee(passedEmployeeTaxID)
    }
}
