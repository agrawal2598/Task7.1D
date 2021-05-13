package com.example.notesapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notesapp.InputUtils
import com.example.notesapp.R
import com.example.notesapp.data.DatabaseHelper
import com.example.notesapp.ui.BaseFragment

class CreateNotesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteText = view.findViewById<EditText>(R.id.notes_edit_text)
        val databaseHelper = DatabaseHelper.getInstance(requireContext())
        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            val text = noteText.text.toString()
            if (text.isNotEmpty()) {
                databaseHelper?.insertNote(text)
                activity?.let { it1 -> InputUtils.hideSoftKeyboard(it1) }
                navController.navigateUp()
            } else
                Toast.makeText(context, "Please enter text", Toast.LENGTH_SHORT).show()
        }
    }
}