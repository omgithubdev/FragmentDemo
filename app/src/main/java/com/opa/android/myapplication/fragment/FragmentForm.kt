package com.opa.android.myapplication.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.opa.android.myapplication.MainActivityViewModel
import com.opa.android.myapplication.R
import com.opa.android.myapplication.databinding.FragmentFormBinding
import com.opa.android.myapplication.model.Person
import java.text.SimpleDateFormat
import java.util.*


class FragmentForm : Fragment() {

    lateinit var viewModel: MainActivityViewModel

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private val mCalendar = Calendar.getInstance()

    companion object {
        fun newInstance() = FragmentForm()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setObserver()
        setListener()
    }

    private fun setListener() {
        binding.btnNext.setOnClickListener {
            if (isValid()) {
                viewModel.updateDetails(
                    Person(
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etDob.text.toString()
                    )
                )
            }
        }

        binding.etDob.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(), date, mCalendar
                    .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.maxDate = Date().time
            datePicker.show()
        }
    }

    private fun isValid(): Boolean {
        var isValid = true

        if (binding.etFirstName.text.isNullOrBlank()) {
            binding.etFirstName.setError(getString(R.string.error_valid_input))
            isValid = false
        }

        if (binding.etLastName.text.isNullOrBlank()) {
            binding.etLastName.setError(getString(R.string.error_valid_input))
            isValid = false
        }

        if (binding.etDob.text.isNullOrBlank()) {
            binding.etDob.setError(getString(R.string.error_valid_input))
            isValid = false
        }

        return isValid
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun setObserver() {

    }

    var date =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            mCalendar.set(Calendar.YEAR, year)
            mCalendar.set(Calendar.MONTH, monthOfYear)
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.etDob.setText(sdf.format(mCalendar.getTime()))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}