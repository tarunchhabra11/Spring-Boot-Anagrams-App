package de.telekom.interviewexercise.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Added below 3 lines to allow opening the h2-console
        http.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("/console/**").permitAll();
        http.headers().frameOptions().disable();
        http.headers().frameOptions().sameOrigin();
    }
}
