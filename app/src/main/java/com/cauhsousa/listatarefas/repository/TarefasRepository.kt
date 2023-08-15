package com.cauhsousa.listatarefas.repository

import com.cauhsousa.listatarefas.datasource.DataSource
import com.cauhsousa.listatarefas.model.Tarefa
import kotlinx.coroutines.flow.Flow

class TarefasRepository(

) {
    private val dataSource= DataSource()
    fun salvarTarefa(tarefa:String, descricao: String, prioridade: Int){
        dataSource.salvarTarefa(tarefa,descricao,prioridade)
    }

    fun recuperarTarefas(): Flow<MutableList<Tarefa>> {
        return dataSource.recuperarTarefas()
    }
}