package com.swlee.flickrsearcher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swlee.flickrsearcher.network.WebClient
import com.swlee.flickrsearcher.repo.FlickrSearchRepoImpl
import com.swlee.flickrsearcher.repo.ExecuteSearch
import com.swlee.flickrsearcher.theme.FlickrSearchTheme
import com.swlee.flickrsearcher.ui.FlickrSearchScreen
import com.swlee.flickrsearcher.ui.FlickrViewModel
import com.swlee.flickrsearcher.ui.ImageDetailScreen

class FlickrSearchActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = FlickrViewModel(ExecuteSearch(FlickrSearchRepoImpl(WebClient.client)))

        setContent() {
            FlickrSearchTheme(darkTheme = false) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "search") {
                    composable("search") {
                        FlickrSearchScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("detail/{index}") { backStackEntry ->
                        val index = backStackEntry.arguments?.getString("index")?.toIntOrNull()
                        val image = viewModel.images.value[index ?: 0]
                        ImageDetailScreen(image = image)
                    }
                }
            }
        }
    }
}