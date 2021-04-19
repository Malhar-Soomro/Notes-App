package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_saved_notes.*

class SavedNotes : AppCompatActivity(), InterfaceNotesAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_notes)
        this.title = "Saved Notes"

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this, this)
        recyclerView.adapter = adapter


        viewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {
            list->
            list?.let {
                adapter.updateList(it)
            }

        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted", Toast.LENGTH_LONG).show()

    }
}