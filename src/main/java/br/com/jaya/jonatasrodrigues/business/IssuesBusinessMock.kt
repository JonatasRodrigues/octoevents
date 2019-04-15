package br.com.jaya.jonatasrodrigues.business

import br.com.jaya.jonatasrodrigues.business.IssuesBusiness
import br.com.jaya.jonatasrodrigues.domain.Event
import br.com.jaya.jonatasrodrigues.domain.Issue
import br.com.jaya.jonatasrodrigues.repositorio.IssueRepository
import org.jetbrains.exposed.sql.statements.InsertStatement
import org.koin.standalone.inject

class IssuesBusinessMock : IssuesBusiness {

    val events = hashMapOf(
            0 to Event("opened", Issue(1, "2019-04-12","teste1@teste.com",321)),
            1 to Event("closed", Issue(2, "2019-04-12","teste2@teste.com",432))
    )

    private val issueRepository by inject<IssueRepository>()

    override fun save(event: Event) = issueRepository.save(event)

    override fun getEvents(issueNumber:Long) : List<Event>{
        val list : MutableList<Event> = mutableListOf<Event>()

        events.forEach{k,v ->
            if(v.issue.number == issueNumber)
                list.add(v)
        }

        return list
    }

}