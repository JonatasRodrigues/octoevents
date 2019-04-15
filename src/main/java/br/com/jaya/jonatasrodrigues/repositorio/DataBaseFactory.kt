package br.com.jaya.jonatasrodrigues.repositorio

import org.jetbrains.exposed.sql.Database

object DataBaseFactory{

    private val urlConnection = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private val driver = "org.h2.Driver"

    fun openConnection(){
        Database.connect(urlConnection, driver = driver)
    }
}