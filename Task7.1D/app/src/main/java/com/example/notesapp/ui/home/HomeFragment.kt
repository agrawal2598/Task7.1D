package com.example.notesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.BaseActivity
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.ui.BaseFragment


class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.create_note_button).setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_createNotesFragment)
        }

        view.findViewById<Button>(R.id.show_all_button).setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_notesListingFragment)
        }
    }
    override fun onResume() {
        super.onResume()
        (activity as BaseActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as BaseActivity).supportActionBar?.show()
    }
}