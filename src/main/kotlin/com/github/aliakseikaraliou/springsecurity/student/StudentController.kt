package com.github.aliakseikaraliou.springsecurity.student

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class StudentController {
    private val students = listOf(
            Student(1, "James Bond"),
            Student(2, "Maria Jones"),
            Student(3, "Anna Smith")
    )

    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Int): Student {
        return students
                .first { id == it.id }
    }
}