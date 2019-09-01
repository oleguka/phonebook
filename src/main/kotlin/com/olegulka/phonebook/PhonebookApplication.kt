package com.olegulka.phonebook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class PhonebookApplication

fun main(args: Array<String>) {
    runApplication<PhonebookApplication>(*args)
}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = ["scheduling.enabled"], matchIfMissing = true)
class SchedulingConfiguration
