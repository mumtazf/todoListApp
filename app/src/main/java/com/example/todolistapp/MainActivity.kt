package com.example.todolistapp

import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var adapter : TaskItemAdapter //lateinit means that it will initialize things later
    var listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onItemLongClicked(position: Int) {
                //1. Remove the item from the list
                    listOfTasks.removeAt(position)
                //2. Notify the adapter that our data set has changed
                    adapter.notifyDataSetChanged()

           }

        }

        findViewById<Button>(R.id.addButton).setOnClickListener{
            Log.i("Button", "User clicked on Add button")
            //realize that tag and msg categories pop up on their own when you just use the curly braces

        }

        //look up recyclerView in our layout

        val recyclerViewLook = findViewById<RecyclerView>(R.id.recyclerView)

        //did not understand this

        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        recyclerViewLook.adapter = adapter
        recyclerViewLook.layoutManager  = LinearLayoutManager(this)


    //set up the button and input field so that the user can input the task

        //Get a reference to the button and set an onClick listener
        findViewById<Button>(R.id.addButton).setOnClickListener()
        {

            val referenceForAddText = findViewById<EditText>(R.id.addTask)
            //1/ Grab the text the user has inputted
            val userInputtedTask = referenceForAddText.text.toString()
            //We convert it into string because not doing so just returns an editable
            //an editable is not very useful since we cannot use it to add in our display of tasks


            //2. Add the string to our list of tasks
            listOfTasks.add(userInputtedTask)

            //notify the adapter that our data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)


            //3. Reset the task input area after task is added
            referenceForAddText.setText("")

        }
        //Save the data that the user has inputted


        //Save data by writing and reading from a file


        //Create a method to get the file we need
        fun getDataFile() : File {

            //Every line is going to represent a specific task in our list of tasks
            return File(filesDir, "data.txt")
        }

        //Method to load the items by reading every line in the data file
        fun loadItems(){
            try {
                //listOfTasks = (getDataFile(), Charset.defaultCharset())
            } catch (iOException: IOException){
                iOException.printStackTrace()
            }



        }

        //Save items - write things into our file
        fun saveItems(){
            try {
                filesDir.writeText(listOfTasks.toString())
            } catch(ioException : IOException){
                ioException.printStackTrace()
            }
        }










    }
}