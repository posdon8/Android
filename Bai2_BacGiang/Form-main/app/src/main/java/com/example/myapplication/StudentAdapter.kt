package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var studentList = listOf<Student>()
    private var filteredList = listOf<Student>()

    init {
        filteredList = studentList
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvStudentId: TextView = itemView.findViewById(R.id.tvStudentId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = filteredList[position]
        holder.tvName.text = student.name
        holder.tvStudentId.text = student.studentId
    }

    override fun getItemCount() = filteredList.size

    fun setStudents(students: List<Student>) {
        studentList = students
        filteredList = students
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.length <= 2) {
            studentList
        } else {
            studentList.filter { student ->
                student.name.contains(query, ignoreCase = true) ||
                        student.studentId.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
