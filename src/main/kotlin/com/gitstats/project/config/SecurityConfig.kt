package com.gitstats.project.config

import com.gitstats.project.security.AuthProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig (@Autowired val authProvider : AuthProvider){

    @Autowired
    fun registerProvider(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .formLogin { formLogin: FormLoginConfigurer<HttpSecurity?> ->
                formLogin
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/process-login")
                    .defaultSuccessUrl("/track")
                    .failureUrl("/auth/login")
            }
            .authorizeHttpRequests { rmr ->
                rmr
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/track/**").authenticated()
                    .anyRequest().permitAll()
            }
            .logout { it.logoutUrl("/logout").logoutSuccessUrl("/auth/login");}
        return http.build()
    }
}
