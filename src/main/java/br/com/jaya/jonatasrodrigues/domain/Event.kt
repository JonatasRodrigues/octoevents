package br.com.jaya.jonatasrodrigues.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class Event ( var action : String, var issue : Issue)