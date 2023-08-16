package com.cauhsousa.listatarefas.datasource

import com.cauhsousa.listatarefas.model.Tarefa
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DataSource {
    private val db = FirebaseFirestore.getInstance()

    private val _todastarefas = MutableStateFlow<MutableList<Tarefa>>(mutableListOf())
    private val todasTarefas: StateFlow<MutableList<Tarefa>> = _todastarefas

    fun salvarTarefa(
        tarefa: String,
        descricao: String,
        nivelPrioridade: Int
    ){

        val tarefaMap = hashMapOf(
            "tarefa" to tarefa,
            "descricao" to descricao,
            "prioridade" to nivelPrioridade
        )
        db.collection("tarefas").document(tarefa).set(tarefaMap).addOnCompleteListener{

        }.addOnFailureListener{

        }
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>>{
        val listaTarefas: MutableList<Tarefa> = mutableListOf()
        db.collection("tarefas").get().addOnCompleteListener{
            if (it.isSuccessful){
                for(document in it.result){
                    val tarefa = document.toObject(Tarefa::class.java)
                    listaTarefas.add(tarefa)
                    _todastarefas.value = listaTarefas

                }
            }
        }
        return todasTarefas
    }

    fun deletarTarefa(tarefa: String){
        db.collection("tarefa").document(tarefa).delete().addOnCompleteListener {

        }.addOnFailureListener{

        }
    }
}