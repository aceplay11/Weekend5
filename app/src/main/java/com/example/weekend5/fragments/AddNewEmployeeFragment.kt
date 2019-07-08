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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddNewEmployeeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddNewEmployeeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddNewEmployeeFragment : Fragment(), View.OnClickListener {


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
        return inflater.inflate(R.layout.fragment_add_new_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmitEntryToDatabase.setOnClickListener(this)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
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
        fun passedEmployeeObject(employee: Employee)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNewEmployeeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNewEmployeeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(view : View?) {
        when (view?.id) {
            R.id.btnSubmitEntryToDatabase -> {
                Log.d("TAG", "SubmitButton onclick")
                if (etFirstNameInput.text.isBlank() || etLastNameInput.text.isBlank() || etAddressInput.text.isBlank()
                    || etCityInput.text.isBlank() || etStateInput.text.isBlank() || etZipInput.text.isBlank()
                    || etTaxIDInput.text.isBlank() || etPositionInput.text.isBlank() || etDepartmentInput.text.isBlank()
                ) {
                    Toast.makeText(activity, "Error: One or more Input Fields are empty.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val newEmployeeEntry = Employee(
                        etFirstNameInput.text.toString(), etLastNameInput.text.toString(),
                        etAddressInput.text.toString(), etCityInput.text.toString(), etStateInput.text.toString(),
                        etZipInput.text.toString(), etTaxIDInput.text.toString(), etPositionInput.text.toString(),
                        etDepartmentInput.text.toString()
                    )
                    listener?.passedEmployeeObject(newEmployeeEntry)
                }
            }
            else -> { }
        }
    }
}
