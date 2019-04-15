package br.com.jaya.jonatasrodrigues.business

import br.com.jaya.jonatasrodrigues.domain.Event
import br.com.jaya.jonatasrodrigues.repositorio.IssueRepository
import org.koin.standalone.inject

class IssuesBusinessImpl : IssuesBusiness {

    private val issueRepository by inject<IssueRepository>()

    override fun save(event: Event) = issueRepository.save(event)

    override fun getEvents(issueNumber:Long) = issueRepository.getEvent(issueNumber)

}