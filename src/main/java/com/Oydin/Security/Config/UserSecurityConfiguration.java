package com.Oydin.Security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public UserSecurityConfiguration(@Lazy UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
     protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/students/save").hasRole("ADMIN")
                .antMatchers("/students/update").hasRole("ADMIN")
                .antMatchers("/students/delete").hasRole("ADMIN")
                .antMatchers("students/*").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
