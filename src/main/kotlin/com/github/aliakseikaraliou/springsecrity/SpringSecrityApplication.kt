package com.github.aliakseikaraliou.springsecrity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringSecrityApplication

fun main(args: Array<String>) {
    runApplication<SpringSecrityApplication>(*args)
}
