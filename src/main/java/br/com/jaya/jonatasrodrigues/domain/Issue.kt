package br.com.jaya.jonatasrodrigues.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class Issue (var id: Long, var created_at : String, var url:String, var number: Long )