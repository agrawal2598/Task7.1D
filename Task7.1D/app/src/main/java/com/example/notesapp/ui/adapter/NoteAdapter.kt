package com.example.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.model.Note


class NoteAdapter(private val notes: List<Note>, val param: NoteOnClickListener) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textLabel: TextView = itemView.findViewById(R.id.text_label)
        val timeLabel: TextView = itemView.findViewById(R.id.time_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.textLabel.text = note.text
        holder.timeLabel.text = note.timeStamp


        holder.itemView.setOnClickListener {
            param.onClickListener(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    interface NoteOnClickListener {
        fun onClickListener(note:Note)
    }
}