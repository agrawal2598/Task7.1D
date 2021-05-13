package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

open class BaseActivity : AppCompatActivity() {

    lateinit var navController: NavController
}