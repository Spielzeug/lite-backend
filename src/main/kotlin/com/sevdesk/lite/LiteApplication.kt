package com.sevdesk.lite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LiteApplication {}

fun main(args: Array<String>) {
    runApplication<LiteApplication>(*args)
}
