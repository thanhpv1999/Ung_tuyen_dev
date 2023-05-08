package com.nhom20.cimeemappserver.config;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nhom20.cimeemappserver.filter.JwtRequestFilter;
import com.nhom20.cimeemappserver.generator.BarcodeGenerator;
import com.nhom20.cimeemappserver.handler.LoginSuccessHandler;
import com.nhom20.cimeemappserver.userdetails.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoSecurityConfig {
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		return authProvider;
	}

	// phan quyen
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.formLogin().loginPage("/login").usernameParameter("email").passwordParameter("pass").permitAll();
//
//		http.formLogin().defaultSuccessUrl("/").failureUrl("/login?error");
//		http.formLogin().successHandler(this.logSuccessHandler);
//		http.authorizeRequests().requestMatchers("/").permitAll().requestMatchers("/customer/**")
//				.access("hasRole('ROLE_ADMIN')");
//		http.csrf().disable();
//		http.authorizeRequests().and().oauth2Login().loginPage("/login").userInfoEndpoint()
//				.userService(oAuthUserService).and().successHandler(oAuth2LoginSusscesHandler);
//		http.logout().permitAll();
//	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/register").permitAll();

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}

	@Bean
	public BarcodeGenerator getBarcodeGenerator() {
		return new BarcodeGenerator();
	}

	@Autowired
	private LoginSuccessHandler logSuccessHandler;

}
