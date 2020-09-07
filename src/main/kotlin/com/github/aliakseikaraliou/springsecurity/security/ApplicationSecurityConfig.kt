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

@Configuration
@EnableWebSecurity
class ApplicationSecurityConfig(val passwordEncoder: PasswordEncoder) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers(HttpMethod.POST, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name)
                .antMatchers(HttpMethod.PUT, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name)
                .antMatchers(HttpMethod.DELETE, "/api/management/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name)
                .antMatchers("/api/management/**").hasAnyRole(ADMIN.name, ADMIN_TRAINEE.name)
                .anyRequest().authenticated()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val annaSmithUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name)
                .authorities(STUDENT.authorities)
                .build()

        val lindaUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name)
                .authorities(ADMIN.authorities)
                .build()

        val tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
//                .roles(ADMIN_TRAINEE.name)
                .authorities(ADMIN_TRAINEE.authorities)
                .build()

        return InMemoryUserDetailsManager(
                annaSmithUser,
                lindaUser,
                tomUser
        )
    }
}