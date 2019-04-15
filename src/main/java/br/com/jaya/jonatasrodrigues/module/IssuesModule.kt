package br.com.jaya.jonatasrodrigues.module

import br.com.jaya.jonatasrodrigues.business.IssuesBusiness
import br.com.jaya.jonatasrodrigues.business.IssuesBusinessImpl
import br.com.jaya.jonatasrodrigues.repositorio.IssueRepository
import br.com.jaya.jonatasrodrigues.repositorio.IssueRepositoryImpl
import org.koin.dsl.module.module

//definir modulos para o koin realizar a DI
val issueModule = module {

    single<IssuesBusiness> { IssuesBusinessImpl() }
    single<IssueRepository> { IssueRepositoryImpl() }
}

