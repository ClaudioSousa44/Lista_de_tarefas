package com.cauhsousa.listatarefas.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cauhsousa.listatarefas.R
import com.cauhsousa.listatarefas.itemLista.TarefaItem
import com.cauhsousa.listatarefas.model.Tarefa
import com.cauhsousa.listatarefas.repository.TarefasRepository
import com.cauhsousa.listatarefas.ui.theme.Verde44
import kotlin.math.log


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTarefas(navController: NavController) {

    val tarefasRepositorio = TarefasRepository()
    val context = LocalContext.current

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

    ) {
        val listaTarefas = tarefasRepositorio.recuperarTarefas().collectAsState(mutableListOf()).value
        Log.e("", "${listaTarefas}")

        LazyColumn(
            modifier = Modifier.padding(top = 70.dp)
        ){
         items(listaTarefas){
             TarefaItem(
                 titulo = it.tarefa!!,
                 descricao =it.descricao!! ,
                 nivelPrioridade = it.prioridade!!,
                 context
             )
         }
        }
    }

}

