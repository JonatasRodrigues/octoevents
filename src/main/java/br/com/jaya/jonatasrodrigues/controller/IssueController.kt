package br.com.jaya.jonatasrodrigues.controller

import br.com.jaya.jonatasrodrigues.business.IssuesBusiness
import br.com.jaya.jonatasrodrigues.domain.Event
import io.javalin.Context
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object IssueController : KoinComponent {

    private val issuesBusiness by inject<IssuesBusiness>()

    fun save(ctx: Context){
        issuesBusiness.save(ctx.body<Event>()).apply {
            ctx.status(201).json(mapOf("messagem" to "Registro salvo com sucesso"))
        }
    }

    fun getEvents(ctx: Context) {
        ctx.json(issuesBusiness.getEvents(ctx.pathParam("issue-number").toLong()))
    }
}