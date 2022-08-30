package com.example.prueba.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWeb extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/js/*", "/images/*", "/**")
                .permitAll();


                // .and().formLogin()
                // .loginPage("/login")
                // .loginProcessingUrl("/logincheck")
                // .usernameParameter("email")
                // .passwordParameter("password")
                // .defaultSuccessUrl("/inicio")
                // .permitAll()
                // .and().logout()
                // .logoutUrl("/logout")
                // .logoutSuccessUrl("/")
                // .permitAll();
    }
}
