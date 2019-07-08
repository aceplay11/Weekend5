package com.example.weekend5.model.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.weekend5.CreateEmployeeDB.EmployeeDB.Companion.CREATE_EMPLOYEE_TABLE
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.DATABASE_NAME
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.DATABASE_TABLE
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.DATABASE_VERSION
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_CITY
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_DEPARTMENT
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_FIRSTNAME
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_LASTNAME
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_POSITION
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_STATE
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_STREETADDRESS
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_TAXID
import com.example.weekend5.DatabaseContract.EmployeeEntry.Companion.FIELD_ZIPCODE
import com.example.weekend5.Employee
import java.lang.Exception


class EmployeeDBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(
    context,
    DATABASE_NAME, factory, DATABASE_VERSION
) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {

        sqLiteDatabase?.execSQL(CREATE_EMPLOYEE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    @Throws(SQLiteConstraintException::class)
    fun insertNewEmployee(employee: Employee): Boolean {

        //write to database
        val sqLiteDatabase = writableDatabase

        //Inputs for new entry from employee object
        val newEntry = ContentValues()
        newEntry.put(FIELD_FIRSTNAME, employee.firstName)
        newEntry.put(FIELD_LASTNAME, employee.lastName)
        newEntry.put(FIELD_STREETADDRESS, employee.streetAddress)
        newEntry.put(FIELD_CITY, employee.city)
        newEntry.put(FIELD_STATE, employee.state)
        newEntry.put(FIELD_ZIPCODE, employee.zipCode)
        newEntry.put(FIELD_TAXID, employee.taxID)
        newEntry.put(FIELD_POSITION, employee.jobPosition)
        newEntry.put(FIELD_DEPARTMENT, employee.department)

        sqLiteDatabase.insert(DATABASE_TABLE, null, newEntry)

        return true
    }


    @Throws(SQLiteConstraintException::class)
    fun deleteEmployee(employeeTaxID: String): Boolean {

        val sqLiteDatabase = writableDatabase

        val selectedEmployee = "$FIELD_TAXID = ?"

        val selectedTaxID = arrayOf(employeeTaxID)
        sqLiteDatabase.delete(DATABASE_TABLE, selectedEmployee, selectedTaxID)
        return true
    }



    fun returnOneEmployee(employeeTaxID: String): ArrayList<Employee> {

        val selectedEmployee = ArrayList<Employee>()
        val sqLiteDatabase = readableDatabase
        val cursor: Cursor?
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM $DATABASE_TABLE WHERE $FIELD_TAXID = '$employeeTaxID'", null)
        } catch (e: Exception) {
            sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE)
            return ArrayList()
        }
        var first: String
        var last: String
        var address: String
        var city: String
        var state: String
        var zip: String
        var taxID: String
        var pos: String
        var dep: String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                first = cursor.getString(cursor.getColumnIndex(FIELD_FIRSTNAME))
                last = cursor.getString(cursor.getColumnIndex(FIELD_LASTNAME))
                address = cursor.getString(cursor.getColumnIndex(FIELD_STREETADDRESS))
                city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                zip = cursor.getString(cursor.getColumnIndex(FIELD_ZIPCODE))
                taxID = cursor.getString(cursor.getColumnIndex(FIELD_TAXID))
                pos = cursor.getString(cursor.getColumnIndex(FIELD_POSITION))
                dep = cursor.getString(cursor.getColumnIndex(FIELD_DEPARTMENT))

                selectedEmployee.add(Employee(first, last, address, city, state, zip, taxID, pos, dep))
                cursor.moveToNext()
            }
            cursor.close()
        }
        return selectedEmployee
    }

    fun returnDepartmentEmployees(employeeDepartment: String): ArrayList<Employee> {

        val selectedDepartment = ArrayList<Employee>()
        val sqLiteDatabase = readableDatabase
        val cursor: Cursor?
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM $DATABASE_TABLE WHERE $FIELD_DEPARTMENT = '$employeeDepartment'", null)
        } catch (e: Exception) {
            sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE)
            return ArrayList()
        }
        var first: String
        var last: String
        var address: String
        var city: String
        var state: String
        var zip: String
        var taxID: String
        var pos: String
        var dep: String

        if (cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                first = cursor.getString(cursor.getColumnIndex(FIELD_FIRSTNAME))
                last = cursor.getString(cursor.getColumnIndex(FIELD_LASTNAME))
                address = cursor.getString(cursor.getColumnIndex(FIELD_STREETADDRESS))
                city = cursor.getString(cursor.getColumnIndex(FIELD_CITY))
                state = cursor.getString(cursor.getColumnIndex(FIELD_STATE))
                zip = cursor.getString(cursor.getColumnIndex(FIELD_ZIPCODE))
                taxID = cursor.getString(cursor.getColumnIndex(FIELD_TAXID))
                pos = cursor.getString(cursor.getColumnIndex(FIELD_POSITION))
                dep = cursor.getString(cursor.getColumnIndex(FIELD_DEPARTMENT))

                selectedDepartment.add(Employee(first, last, address, city, state, zip, taxID, pos, dep))
                cursor.moveToNext()
            }
            cursor.close()
        }
        return selectedDepartment
    }

    @Throws(SQLiteConstraintException::class)
    fun updateEmployee(updateID: String, employee: Employee) : Boolean{

        //write to database
        val sqLiteDatabase = writableDatabase
        val selectionByKey = "$FIELD_TAXID = ?"

        //Inputs for new entry from employee object
        val updateEntry = ContentValues()
        updateEntry.put(FIELD_FIRSTNAME, employee.firstName)
        updateEntry.put(FIELD_LASTNAME, employee.lastName)
        updateEntry.put(FIELD_STREETADDRESS, employee.streetAddress)
        updateEntry.put(FIELD_CITY, employee.city)
        updateEntry.put(FIELD_STATE, employee.state)
        updateEntry.put(FIELD_ZIPCODE, employee.zipCode)
        updateEntry.put(FIELD_TAXID, employee.taxID)
        updateEntry.put(FIELD_POSITION, employee.jobPosition)
        updateEntry.put(FIELD_DEPARTMENT, employee.department)

        sqLiteDatabase.update(DATABASE_TABLE, updateEntry, selectionByKey, arrayOf(updateID))

        return true
    }

}