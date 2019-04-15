package br.com.jaya.jonatasrodrigues.repositorio

import br.com.jaya.jonatasrodrigues.domain.Event
import org.jetbrains.exposed.sql.statements.InsertStatement

interface IssueRepository {

    fun save(event: Event): InsertStatement<Number>
    fun getEvent(issueNumber: Long): List<Event>
}

