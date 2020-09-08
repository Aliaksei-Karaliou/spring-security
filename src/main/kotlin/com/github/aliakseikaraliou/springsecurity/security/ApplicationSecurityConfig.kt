package com.github.aliakseikaraliou.springsecurity.security

import com.github.aliakseikaraliou.springsecurity.security.ApplicationUserRole.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig(val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers(HttpMethod.POST, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
                .antMatchers(HttpMethod.PUT, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
                .antMatchers(HttpMethod.DELETE, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.permission)
                .antMatchers("/api/management/**").hasAnyRole(ADMIN.name, ADMIN_TRAINEE.name)
                .anyRequest().authenticated()
                .and()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.authorities)
                .build()

        val lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMIN.authorities)
                .build()

        val tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMIN_TRAINEE.authorities)
                .build()

        return InMemoryUserDetailsManager(
                annaSmithUser,
                lindaUser,
                tomUser
        )
    }
}