package com.opa.android.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opa.android.myapplication.model.Person

class MainActivityViewModel : ViewModel() {

    private val mutableDetailsData: MutableLiveData<Person> = MutableLiveData()

    val datailsLiveData: LiveData<Person>
        get() = mutableDetailsData

    fun updateDetails(person: Person){
        mutableDetailsData.postValue(person)
    }

}