package com.sandiarta.advweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sandiarta.advweek4.databinding.StudentListItemBinding
import com.sandiarta.advweek4.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener
{
    class StudentViewHolder(var binding:StudentListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this

//        holder.binding.textViewID.text = studentList[position].id
//        holder.binding.textViewStudentName.text = studentList[position].name
//        holder.binding.buttonDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetailFragment(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imageView, object:Callback {
//                override fun onSuccess() {
//                    holder.binding.progressBarImage.visibility = View.INVISIBLE;
//                    holder.binding.imageView.visibility = View.VISIBLE;
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.d("Cek","Error")
//                }
//
//        })

    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetailFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }


}

