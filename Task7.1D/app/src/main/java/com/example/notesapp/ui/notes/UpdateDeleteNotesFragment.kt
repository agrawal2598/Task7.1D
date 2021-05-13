package com.example.notesapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.notesapp.InputUtils
import com.example.notesapp.R
import com.example.notesapp.data.DatabaseHelper
import com.example.notesapp.data.model.Note
import com.example.notesapp.ui.BaseFragment

class UpdateDeleteNotesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_delete_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteText = view.findViewById<EditText>(R.id.notes_edit_text)
        noteText.setText(arguments?.getString(Note.COLUMN_TEXT).toString())
        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            val text = noteText.text.toString()
            if (text.isNotEmpty()) {
                databaseHelper?.updateNote(text, arguments?.getInt(Note.COLUMN_ID).toString())
                navController.navigateUp()
                activity?.let { it1 -> InputUtils.hideSoftKeyboard(it1) }
            } else
                Toast.makeText(context, "Please enter text", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.delete_button).setOnClickListener {
            databaseHelper?.deleteNote(arguments?.getInt(Note.COLUMN_ID).toString())
            navController.navigateUp()
        }
    }
}