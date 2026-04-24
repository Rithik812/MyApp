package com.example.myapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.presentation.home.HomeScreen
import com.example.myapp.presentation.home.detail.DetailScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen {
                nav.navigate("${Routes.DETAIL}/$it")
            }
        }

        composable("${Routes.DETAIL}/{id}") {
            val id = it.arguments?.getString("id") ?: ""
            DetailScreen(id, onClick = {
                nav.popBackStack()
            })
        }
    }
}