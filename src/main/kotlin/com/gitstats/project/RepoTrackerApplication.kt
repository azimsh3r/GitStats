package com.gitstats.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.web.client.RestClient

@SpringBootApplication
class RepoTrackerApplication {
	@Bean
	fun restClient () = RestClient.create()
}

fun main(args: Array<String>) {
	runApplication<RepoTrackerApplication>(*args)
}
