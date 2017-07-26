package com.zcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zcc.model.dao.User;
import com.zcc.model.repository.UserRepository;

/**
 * Created by NCP-620 on 2017/7/12.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(new UserDetailsService() {
				@Override
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
					User user= userRepository.findUserByUsername(username);
					return user;
				}
			});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/register","/register/saveRegister","/swagger-ui.html")
				//.access("hasRole('USER')").antMatchers("/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successForwardUrl("/validateLogin")
				.permitAll()
				.failureUrl("/login?error=true")
				.and()
			.logout()
			.permitAll();
		http.csrf().disable();

	}
}
