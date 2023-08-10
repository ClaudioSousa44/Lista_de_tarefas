package com.cauhsousa.listatarefas.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DataSource {
    private val db = FirebaseFirestore.getInstance()

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
}