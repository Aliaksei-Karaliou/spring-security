package com.github.aliakseikaraliou.springsecurity.security

import com.github.aliakseikaraliou.springsecurity.security.ApplicationUserPermission.*
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class ApplicationUserRole(val permissions: Set<ApplicationUserPermission>) {
    STUDENT(emptySet()),
    ADMIN(setOf(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMIN_TRAINEE(setOf(COURSE_READ, STUDENT_READ));

    val authorities = permissions
            .map { SimpleGrantedAuthority(it.permission) }
            .toMutableList()
            .apply { add(0, SimpleGrantedAuthority("ROLE_$name")) }
            .toSet()
}