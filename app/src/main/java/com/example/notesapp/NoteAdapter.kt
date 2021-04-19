package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.NoteAdapter.NoteViewHolder
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.note_item.view.*
import java.util.zip.Inflater

class NoteAdapter(private val context: Context, private val listener:InterfaceNotesAdapter): RecyclerView.Adapter<NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textView: TextView = itemView.findViewById(R.id.myTextView)
        val deleteButton : ImageView = itemView.findViewById(R.id.dltButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder{
        val viewHolder =  NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(noteList: List<Note>){
        allNotes.clear()
        allNotes.addAll(noteList)

        notifyDataSetChanged()
    }

}
interface InterfaceNotesAdapter{
    fun onItemClicked(note:Note)
}