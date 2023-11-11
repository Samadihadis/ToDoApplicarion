package com.hadis.todoapplicarion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.collections.immutable.mutate
import kotlinx.coroutines.runBlocking

class TodoAdaptor(var todoList: MutableList<Todo>, var context: Context) :
    RecyclerView.Adapter<TodoAdaptor.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var card = itemView.findViewById<CardView>(R.id.cardView)
        var title = itemView.findViewById<TextView>(R.id.textTitle)
        var description = itemView.findViewById<TextView>(R.id.textDescription)
        var date = itemView.findViewById<TextView>(R.id.textDate)
        var dateView = itemView.findViewById<TextView>(R.id.textViewDate)
        var time = itemView.findViewById<TextView>(R.id.textTime)
        var timeView = itemView.findViewById<TextView>(R.id.textViewTime)
        var isDoneCheckBox = itemView.findViewById<CheckBox>(R.id.isDoneCheckBox)

        init {
            isDoneCheckBox.setOnCheckedChangeListener { button, isSelected ->
               if (isSelected){
                 runBlocking {
                     context.dataStore.updateData {
                         it.copy(
                             it.todoList.mutate {
                                 it.removeAt(adapterPosition)
                             }
                         )
                     }
                 }
                   currentTodosBinding.recycleView.adapter!!.notifyDataSetChanged()
               }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_in_rec_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = todoList[position].title
            description.text = todoList[position].description
            dateView.text = todoList[position].date
            timeView.text = todoList[position].time
            isDoneCheckBox.isChecked = todoList[position].isDone
        }
    }
}