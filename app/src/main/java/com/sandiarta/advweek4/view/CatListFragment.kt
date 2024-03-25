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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sandiarta.advweek4.R
import com.sandiarta.advweek4.databinding.FragmentCatListBinding
import com.sandiarta.advweek4.viewmodel.ListCatViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [CatListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatListFragment : Fragment() {
    private lateinit var viewModel: ListCatViewModel
    private lateinit var binding: FragmentCatListBinding
    private val catListAdapter  = CatListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListCatViewModel::class.java)
        viewModel.refresh()

        Log.d("TEST", "masuk")

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = catListAdapter

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
        viewModel.catsLD.observe(viewLifecycleOwner, Observer {
            catListAdapter.updateCatList(it)
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressBarLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressBarLoad.visibility = View.GONE
            }
        })
        viewModel.catLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.textViewError?.visibility = View.VISIBLE
            }
            else {
                binding.textViewError?.visibility = View.GONE
            }
        })

    }
}