package br.com.jaya.jonatasrodrigues.config

import br.com.jaya.jonatasrodrigues.controller.IssueController
import br.com.jaya.jonatasrodrigues.repositorio.DataBaseFactory
import io.javalin.InternalServerErrorResponse
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import org.koin.standalone.StandAloneContext

class AppConfig (private val port: Int){

    fun init(): Javalin{

        val app = Javalin.create().apply {
            StandAloneContext.startKoin(listOf(issueModule))
            exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
            error(503) { ctx -> ctx.json("Erro ao iniciar o sistema") }
        }.start(port)


        app.routes  {
            ApiBuilder.post("/", IssueController::save)
            ApiBuilder.get("/issues/:issue-number/events", IssueController::getEvents)
        }

        DataBaseFactory.openConnection()

        return app
    }


    //Start do modulo de testes
    fun test(): Javalin{

        val app = Javalin.create().apply {
            StandAloneContext.loadKoinModules(listOf(mockModule))
            exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
            error(503) { ctx -> ctx.json("Erro ao iniciar o sistema") }
        }.start(port)


        app.routes  {
            ApiBuilder.post("/", IssueController::save)
            ApiBuilder.get("/issues/:issue-number/events", IssueController::getEvents)
        }

        DataBaseFactory.openConnection()

        return app
    }
}