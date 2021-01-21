package com.opa.android.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.opa.android.myapplication.MainActivityViewModel
import com.opa.android.myapplication.R
import com.opa.android.myapplication.databinding.FragmentDetailBinding
import com.opa.android.myapplication.databinding.FragmentFormBinding
import com.opa.android.myapplication.model.Person
import com.opa.android.myapplication.util.Utils

class FragmentDetails : Fragment() {

    lateinit var viewModel: MainActivityViewModel

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FragmentDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        setObserver()
        setListener()
    }

    private fun setListener(){
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setObserver() {
        viewModel.datailsLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                updateViews(it)
            }
        })
    }

    private fun updateViews(person: Person) {
        binding.tvName.text =
            "Name: ${person.firstName} ${person.lastName}" +
                    " \n\n" +
                    " Age: ${Utils.getAge(person.dob)}"
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

}