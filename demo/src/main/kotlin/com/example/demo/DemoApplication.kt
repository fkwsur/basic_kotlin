package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableJpaAuditing
@Import(CorsConfig::class)
@PropertySource("classpath:/application.properties")
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
