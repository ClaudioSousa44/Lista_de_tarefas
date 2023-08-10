package com.cauhsousa.listatarefas.repository

import com.cauhsousa.listatarefas.datasource.DataSource

class TarefasRepository(

) {
    private val dataSource= DataSource()
    fun salvarTarefa(tarefa:String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa,descricao,prioridade)
    }
}