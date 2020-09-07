package com.github.aliakseikaraliou.springsecurity.student

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/management/students")
class StudentManagementController {
    private val students = listOf(
            Student(1, "James Bond"),
            Student(2, "Maria Jones"),
            Student(3, "Anna Smith")
    )

    @GetMapping
    fun getStudents() = students

    @PostMapping
    fun registerStudent(@RequestBody student: Student) {
        println(student)
    }

    @DeleteMapping(path = ["{id}"])
    fun deleteStudent(@PathVariable id: Int) {
        println(id)
    }

    @PutMapping(path = ["{id}"])
    fun updateStudent(@PathVariable id: Int,
                      @RequestBody student: Student) {
        println("%s %s".format(id, student))
    }
}