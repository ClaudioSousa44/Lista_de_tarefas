package com.cauhsousa.listatarefas.itemLista

import android.app.AlertDialog
import android.content.Context
import android.view.KeyEvent.DispatcherState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

import com.cauhsousa.listatarefas.R
import com.cauhsousa.listatarefas.repository.TarefasRepository
import com.cauhsousa.listatarefas.ui.theme.RADIO_BUTTON_YELLOW_DISABLED
import com.cauhsousa.listatarefas.ui.theme.ShapePrioridade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TarefaItem(
    titulo: String,
    descricao: String,
    nivelPrioridade: Int,
    context: Context
) {
    val scope = rememberCoroutineScope()
    val tarefasRepository = TarefasRepository()

    fun dialogDeletar(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar Tarefa")
            .setMessage("Deseja excluir tarefa?")
            .setPositiveButton("Sim"){
                _, _ ->
                tarefasRepository.deletarTarefa(titulo)
//                scope.launch ( Dispatchers.Main ){
//                    listaTa
//                }
            }
            .setNegativeButton("Não"){
                    _, _ ->
            }
            .show()
    }

    var prioridade: String = when(nivelPrioridade){
        0 -> {
            "Sem prioridade"
        }
        1 -> {
            "Prioridade Baixa"
        }
        2-> {
            "Prioridade Média"
        }
        else -> {
            "Prioridade Alta"
        }
    }

    val colorPrioridade = when(nivelPrioridade){
        0-> {
            Color.Black
        }
        1 -> {
            Color.Green
        }
        2 -> {
            RADIO_BUTTON_YELLOW_DISABLED
        }
        else -> {
            Color.Red
        }
    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color.White)

    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(20.dp)
        ) {
            val (txtTitulo, txtDescricao, cardPrioridade, txtPrioridade, btDeletar ) =
                createRefs()

            Text(
                text = titulo,
                modifier = Modifier.constrainAs(txtTitulo){
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descricao,
                modifier = Modifier.constrainAs(txtDescricao){
                    top.linkTo(txtTitulo.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = prioridade,
                modifier = Modifier.constrainAs(txtPrioridade){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card (
                colors = CardDefaults.cardColors(colorPrioridade),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPrioridade) {
                        top.linkTo(txtDescricao.bottom, margin = 10.dp)
                        start.linkTo(txtPrioridade.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapePrioridade.small

            ) {}

            IconButton(
                onClick = {
                    dialogDeletar()
                },
                modifier = Modifier.constrainAs(btDeletar){
                    top.linkTo(txtDescricao.bottom, margin = 10.dp)
                    start.linkTo(cardPrioridade.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_delete) ,
                    contentDescription = "Botão de deletar" )
            }
        }
    }
}

//@Preview
//@Composable
//fun TarefaItemPreview() {
//    TarefaItem()
//}