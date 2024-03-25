package com.sandiarta.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sandiarta.advweek4.R
import com.sandiarta.advweek4.databinding.FragmentStudentListBinding
import com.sandiarta.advweek4.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private lateinit var binding: FragmentStudentListBinding
    private val studentListAdapter  = StudentListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        Log.d("TEST", "masuk")

        val recView = binding.recView
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility =  View.GONE
            binding.textViewError.visibility = View.GONE
            binding.progressBarLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            val progressLoad = binding.progressBarLoad
            val recView = binding.recView
            if(it == true) {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            } else {
                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        })
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = binding.textViewError
            if(it == true) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })
    }


}