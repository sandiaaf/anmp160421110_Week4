package com.sandiarta.advweek4.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandiarta.advweek4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
//    val studentLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun updateStudent() {

    }
    fun fetch(id:String) {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1

//        loadingLD.value = true
//        studentLoadErrorLD.value = false


        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=${id}"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val student = Gson().fromJson(response, Student::class.java)
                studentLD.value = student
//                loadingLD.value = false

//                loadingLD.value = false
//                Log.d("showvoley", it)

                Log.d("showvoley", student.toString())
            },
            {
                Log.d("showvoley", it.toString())
//                studentLoadErrorLD.value = false
//                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}