package com.example.weekend5.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weekend5.Employee

import com.example.weekend5.R
import kotlinx.android.synthetic.main.fragment_add_new_employee.*
import kotlinx.android.synthetic.main.fragment_update_employee.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UpdateEmployeeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UpdateEmployeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UpdateEmployeeFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnUpdateEntryToDatabase.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun updateEmployeeObject(taxID: String, employee: Employee)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpdateEmployeeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpdateEmployeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view : View?) {
        when (view?.id) {
            R.id.btnUpdateEntryToDatabase -> {
                Log.d("TAG", "SubmitButton onclick")
                if (etFirstNameUpdate.text.isBlank() || etLastNameUpdate.text.isBlank() || etAddressUpdate.text.isBlank()
                    || etCityUpdate.text.isBlank() || etStateUpdate.text.isBlank() || etZipUpdate.text.isBlank()
                    || etTaxIDUpdate.text.isBlank() || etPositionUpdate.text.isBlank() || etDepartmentUpdate.text.isBlank()
                ) {
                    Toast.makeText(activity, "Error: One or more Input Fields are empty.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val updateEmployeeEntry = Employee(
                        etFirstNameUpdate.text.toString(), etLastNameUpdate.text.toString(),
                        etAddressUpdate.text.toString(), etCityUpdate.text.toString(), etStateUpdate.text.toString(),
                        etZipUpdate.text.toString(), etTaxIDUpdate.text.toString(), etPositionUpdate.text.toString(),
                        etDepartmentUpdate.text.toString()
                    )
                    listener?.updateEmployeeObject(etTaxIDUpdate.text.toString(),updateEmployeeEntry)
                }
            }
            else -> { }
        }
    }
}
