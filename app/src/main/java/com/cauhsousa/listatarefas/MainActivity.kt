package com.cauhsousa.listatarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cauhsousa.listatarefas.ui.theme.ListaTarefasTheme
import com.cauhsousa.listatarefas.view.ListaTarefas
import com.cauhsousa.listatarefas.view.SalvarTarefas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaTarefasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "listaTarefas"){
                        composable(route = "listaTarefas"){ ListaTarefas(navController)}
                        composable(route = "salvarTarefas"){ SalvarTarefas(navController)}


                    }
                }
            }
        }
    }
}

