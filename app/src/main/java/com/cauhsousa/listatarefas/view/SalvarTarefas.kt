package com.cauhsousa.listatarefas.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cauhsousa.listatarefas.R
import com.cauhsousa.listatarefas.components.Botao
import com.cauhsousa.listatarefas.components.CaixaDeTexto
import com.cauhsousa.listatarefas.repository.TarefasRepository
import com.cauhsousa.listatarefas.ui.theme.Azul44
import com.cauhsousa.listatarefas.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.cauhsousa.listatarefas.ui.theme.Verde44
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalvarTarefas(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tarefasRepository = TarefasRepository()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(Verde44),
                title = {
                    Text(
                        text = stringResource(R.string.salvar_tarefas),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

            )
        }
    ) {
        var tituloTarefa by remember {
            mutableStateOf("")
        }

        var descricaoTarefa by remember {
            mutableStateOf("")
        }

        var semPrioridadeTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeBaixaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeMediaTarefa by remember {
            mutableStateOf(false)
        }

        var prioridadeAltaTarefa by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            CaixaDeTexto(
                value = tituloTarefa,
                onValueChanged = {
                                 tituloTarefa = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 70.dp, 20.dp, 0.dp) ,
                label = stringResource(R.string.titulo_tarefa),
                maxLines =  1,
                keyboardType = KeyboardType.Text
            )
            
            CaixaDeTexto(
                value =  descricaoTarefa,
                onValueChanged = {
                                 descricaoTarefa = it
                } ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = stringResource(R.string.descri_o) ,
                maxLines =  5,
                keyboardType = KeyboardType.Text
            )
            
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(R.string.nivel_de_prioridade)
                )

                RadioButton(
                    selected = prioridadeBaixaTarefa ,
                    onClick = {
                        prioridadeBaixaTarefa = !prioridadeBaixaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green,
                        unselectedColor = Color.Green
                    )
                )

                RadioButton(
                    selected = prioridadeMediaTarefa,
                    onClick = {
                        prioridadeMediaTarefa = !prioridadeMediaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = RADIO_BUTTON_YELLOW_DISABLED,
                        unselectedColor = RADIO_BUTTON_YELLOW_DISABLED
                    )
                )

                RadioButton(
                    selected = prioridadeAltaTarefa ,
                    onClick = {
                        prioridadeAltaTarefa = !prioridadeAltaTarefa
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Red,
                        unselectedColor = Color.Red
                    )
                )
            }

            Botao(
                onClick = {
                        var mensagem: Boolean = true
                        scope.launch(Dispatchers.IO){
                            if (tituloTarefa.isEmpty()){
                                mensagem = false
                            }
                        }
                        scope.launch(Dispatchers.Main){
                            if(mensagem){
                                Toast.makeText(context, "Sucesso ao salvar a tarefa",Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(context, "Titulo da tarefa é obrigatório",Toast.LENGTH_SHORT).show()
                            }
                        }

                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(80.dp)  ,
                text = stringResource(R.string.salvar) )
        }

    }
}


