package com.sandiarta.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandiarta.advweek4.R
import com.sandiarta.advweek4.databinding.FragmentStudentDetailBinding
import com.sandiarta.advweek4.databinding.FragmentStudentListBinding
import com.sandiarta.advweek4.viewmodel.DetailViewModel
import com.sandiarta.advweek4.viewmodel.ListViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), StudentNotifClickListener, StudentUpdateClickListener{
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentStudentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        var id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id;
        viewModel.fetch(id)

        binding.listenerUpdate = this
        binding.listenerNotif= this
        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            binding.student = it

//            binding.buttonUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(
//                            student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_24
//                        )
//                    }
//            }

//            Picasso.get().load(student.photoUrl).into(binding.imageView2)
//            binding.txtID.setText(it.id)
//            binding.txtName.setText(it.name)
//            binding.txtBod.setText(it.bod)
//            binding.txtPhone.setText(it.phone)
        })
    }

    override fun onStudentNotifClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(
                    binding.student!!.name!!.toString(),
                    "A new notification created",
                    R.drawable.baseline_person_24
                )
                Log.d("Messages", "Notif")

            }
    }

    override fun onStudentUpdateClick(v: View) {
        Toast.makeText(context, "UPDATE Student "+ binding.student!!.name!!.toString(), Toast.LENGTH_SHORT).show()
    }
}