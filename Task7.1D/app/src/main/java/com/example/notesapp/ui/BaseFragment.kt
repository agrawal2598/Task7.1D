package com.example.notesapp.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.notesapp.BaseActivity

public open class BaseFragment : Fragment() {
    val navController: NavController by lazy {  (activity as BaseActivity).navController }
}