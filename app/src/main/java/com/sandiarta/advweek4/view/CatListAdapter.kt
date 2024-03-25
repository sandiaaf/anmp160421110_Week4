package com.sandiarta.advweek4.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sandiarta.advweek4.databinding.CatListItemBinding
import com.sandiarta.advweek4.model.Cat

class CatListAdapter(val catList:ArrayList<Cat>)
    : RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {
    class CatViewHolder(var binding: CatListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = CatListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CatViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.textViewID.text = catList[position].id
        holder.binding.textViewCatName.text = catList[position].name
        holder.binding.textViewBreed.text = "Breed: "+catList[position].breed
        holder.binding.textViewAge.text = "Age: "+ catList[position].age + " month"
        holder.binding.buttonDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCatList(newCatList: ArrayList<Cat>) {
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged()
    }
}