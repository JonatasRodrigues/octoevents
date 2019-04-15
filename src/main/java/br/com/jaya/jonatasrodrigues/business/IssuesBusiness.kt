package br.com.jaya.jonatasrodrigues.business

import br.com.jaya.jonatasrodrigues.domain.Event
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.koin.standalone.KoinComponent

interface IssuesBusiness : KoinComponent {
   fun save(event: Event): InsertStatement<Number>
   fun getEvents(issueNumber:Long):List<Event>
}

