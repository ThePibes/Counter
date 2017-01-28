package com.cosa.conf;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Alessandro
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    //.successForwardUrl("/stock")
                    .failureForwardUrl("/login/error")
                    .failureUrl("/login/error")
                .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/stock/**").access("hasRole('ROLE_USER')")
                    //.failureForwardUrl("/login")
                    //.loginProcessingUrl("/login");
                .and()
                .logout()
                .logoutSuccessUrl("/login");

                   // .defaultSuccessUrl("/stock.html");
    }
}
