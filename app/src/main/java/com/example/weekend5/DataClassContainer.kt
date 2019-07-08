package com.example.weekend5
import android.provider.BaseColumns
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.DATABASE_TABLE
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_CITY
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_DEPARTMENT
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_FIRSTNAME
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_LASTNAME
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_POSITION
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_STATE
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_STREETADDRESS
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_TAXID
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_ZIPCODE

data class Employee (var firstName : String, var lastName : String, var streetAddress : String, var city : String,
                     var state : String, var zipCode : String, var taxID : String, var jobPosition : String,
                     var department : String)



object DatabaseContract {
    class EmployeeEntry : BaseColumns {
        companion object {
            const val DATABASE_NAME = "employees"
            const val DATABASE_VERSION = 1
            const val DATABASE_TABLE = "employee"
            const val FIELD_FIRSTNAME = "first_name"
            const val FIELD_LASTNAME = "last_name"
            const val FIELD_STREETADDRESS = "street_address"
            const val FIELD_CITY = "city"
            const val FIELD_STATE = "state"
            const val FIELD_ZIPCODE = "zip_code"
            const val FIELD_TAXID = "TaxID"
            const val FIELD_POSITION = "position"
            const val FIELD_DEPARTMENT = "department"

        }
    }
}

object CreateEmployeeDB{
    class EmployeeDB{
        companion object{
            const val CREATE_EMPLOYEE_TABLE = "CREATE TABLE $DATABASE_TABLE ($FIELD_FIRSTNAME TEXT, $FIELD_LASTNAME TEXT, " +
                    "$FIELD_STREETADDRESS, $FIELD_CITY TEXT, $FIELD_STATE TEXT, $FIELD_ZIPCODE TEXT, " +
                    "$FIELD_TAXID TEXT PRIMARY KEY, $FIELD_POSITION TEXT, $FIELD_DEPARTMENT TEXT)"
        }
    }
}




