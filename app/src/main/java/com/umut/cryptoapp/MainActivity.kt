package com.umut.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umut.cryptoapp.ui.theme.CryptoAppTheme
import com.umut.cryptoapp.view.CryptoDetailScreen
import com.umut.cryptoapp.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {
                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(navController = navController, startDestination = "crypto_list_screen") {
                            composable("crypto_list_screen") {
                                CryptoListScreen(navController = navController)
                            }
                            composable(
                                route = "crypto_detail_screen/{symbol}",
                                arguments = listOf(
                                    navArgument("symbol") {
                                        type = NavType.StringType
                                    }
                                )
                            ) { backStackEntry ->
                                val symbol = backStackEntry.arguments?.getString("symbol") ?: ""
                                CryptoDetailScreen(symbol = symbol)
                            }
                        }
                    }
                }
            }
        }
    }
}
