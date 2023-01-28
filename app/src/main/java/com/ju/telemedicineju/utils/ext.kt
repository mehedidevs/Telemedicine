package com.ju.telemedicineju

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationBarView

public fun NavigationBarView.bottomNavSet(navController: NavController) {
    NavigationUI.setupWithNavController(this, navController)
}

public fun Activity.navControllerProvider(fraId: Int): NavController {
    return Navigation.findNavController(this, fraId)
}

public fun Any.toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}