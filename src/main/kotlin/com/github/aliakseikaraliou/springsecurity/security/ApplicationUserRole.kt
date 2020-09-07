package com.github.aliakseikaraliou.springsecurity.security

import com.github.aliakseikaraliou.springsecurity.security.ApplicationUserPermission.*

enum class ApplicationUserRole(val permissions: Set<ApplicationUserPermission>) {
    STUDENT(emptySet()),
    ADMIN(setOf(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE))
}