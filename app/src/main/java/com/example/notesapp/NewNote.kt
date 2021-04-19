package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_new_note.*


class NewNote : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        this.title = "New Note"


        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application)
        ).get(NoteViewModel::class.java)

    }

    fun submitNote(view: View) {
        val userText = inputText.text.toString()
        if (userText.isNotEmpty()) {
            viewModel.insertNote(Note(userText))
            Toast.makeText(this, "$userText Inserted", Toast.LENGTH_LONG).show()
            inputText.setText("")

        }
        
    }
}