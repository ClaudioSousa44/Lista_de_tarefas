package com.cauhsousa.listatarefas.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cauhsousa.listatarefas.R
import com.cauhsousa.listatarefas.ui.theme.Verde44

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.lista_de_tarefas),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(Verde44)
            )
        },
        containerColor = Color.Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                        navController.navigate("salvarTarefas")
            },
                containerColor = Verde44
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add) ,
                    contentDescription = "Icone de adicionar",
                    tint = Color.White)

            }
        }

    ) {}

}

//@Preview(showBackground = true)
//@Composable
//fun ListaTarefasPreview() {
//    ListaTarefas()
//}