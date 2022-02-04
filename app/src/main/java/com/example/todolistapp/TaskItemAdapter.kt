package com.example.todolistapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Tells the recycleview how to display the data we provide to the todo app


class TaskItemAdapter( val listOfItems : List<String>,
                       val longClickListener : OnLongClickListener)
    : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {


    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Store references to elements in our layout view

        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                    longClickListener.onItemLongClicked(adapterPosition)
                    true
            }
        }
    }










    //number of items in the list
    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Usually involves inflating a layout from XML and returning the holder
    //how to lay out each item and what is the weight on each items
    //uniformity within
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//1. Get the data model based on the position
        val item = listOfItems.get(position)

        holder.textView.text = item

        //this class basically helps you bind the holder according to the different tasks
        //it helps you get an idea of what text you will display to the user, based on the input you are getting from them
    }


    //removing an item from the list using a longClickListener














}