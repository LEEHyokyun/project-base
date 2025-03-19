package com.hyokyunp1.hyokyunp1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.hyokyunp1"})
public class WebSecurityConfig {
	
	/*
	 * dataSource is same with the infor at application.properties
	 * */
	@Autowired
	private DataSource dataSource;
//	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/account/register","/api/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/account/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return httpSecurity.build();
	}
	
	//Authentication By Data Base
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED "
				+"FROM THYM_USER "
				+"WHERE USERNAME = ?")
			.authoritiesByUsernameQuery("SELECT U.USERNAME, R.NAME "
				+"FROM THYM_USER_ROLE UR "
				+"INNER JOIN THYM_USER U "
				+"ON UR.USER_ID = U.ID "
				+"INNER JOIN THYM_ROLE R "
				+"ON UR.ROLE_ID = R.ID "
				+"WHERE U.USERNAME = ?");	
	}

	/*
	 * give the Encrypted password
	 * */
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	//Temporary User : it could be used as a bean or @AutoWired because of "Configuration" annotation.
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}