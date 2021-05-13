package com.example.notesapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.DatabaseHelper
import com.example.notesapp.data.model.Note
import com.example.notesapp.ui.BaseFragment
import com.example.notesapp.ui.adapter.NoteAdapter

class NotesListingFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        val notes = view.findViewById<RecyclerView>(R.id.notes_recycler_view)
        val notesNotFound = view.findViewById<TextView>(R.id.no_notes_found_label)
        val notesList = databaseHelper?.getNotes()
        notes.adapter = notesList?.let {
            NoteAdapter(it, object : NoteAdapter.NoteOnClickListener {
                override fun onClickListener(note: Note) {
                    val bundle = Bundle()
                    bundle.putInt(Note.COLUMN_ID, note.id)
                    bundle.putString(Note.COLUMN_TEXT, note.text)
                    navController.navigate(
                        R.id.action_notesListingFragment_to_updateDeleteNotesFragment,
                        bundle
                    )
                }
            })
        }
        if (notesList?.isNullOrEmpty() == true)
            notesNotFound.visibility = View.VISIBLE

    }
}