package com.beerhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${user-authentication}")
	private String userAuthentication;
	
	@Value("${password-authentication}")
	private String passwordAuthentication;
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .authorizeRequests().anyRequest().authenticated()
        .and()
        .httpBasic();
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication()
            .withUser(userAuthentication)
            .password("{noop}" +passwordAuthentication)
            .roles("ADMIN");
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
