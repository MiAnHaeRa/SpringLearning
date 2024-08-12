package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean	//비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		// 가장 많이 쓰이는 방식
		return new BCryptPasswordEncoder();
	}
	
	//인증 및 인가
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http	//Security가 적용될 URI
			.authorizeHttpRequests((authrize)
					-> authrize
						.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()	//forward가 일어날때 시큐리티 없이 진행
//						.requestMatchers("/empList").authenticated()	//해당 경로는 인증만 되었으면 접근 가능
						.requestMatchers("/", "/all").permitAll()		//해당 경로는 시큐리티 없이 진행
						.requestMatchers("/user/**").hasRole("USER")	//hasAnyRole => 여러 권한 승인
						.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")	
						.anyRequest().permitAll())		//나머지 모든 경로 시큐리티 없이 진행
			.formLogin(formLogin 
					-> formLogin
						.defaultSuccessUrl("/all"))
			.logout(logout 
					-> logout
						.logoutSuccessUrl("/all")
						.invalidateHttpSession(true));	//관련 정보 Session에서 삭제
			
		
		return http.build();
	}
	
	/*
	@Bean		//메모리상 인증정보 등록 => 테스트 전용 방식
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							.username("user01")
							.password(passwordEncoder().encode("1234"))
							.roles("USER")	//ROLE_USER
//							.authorities("ROLE_USER")
							.build();
		
		UserDetails admin = User.builder()
							.username("admin01")
							.password(passwordEncoder().encode("1234"))
//							.roles("ADMIN")	//ROLE_ADMIN
							.authorities("ROLE_ADMIN", "ROLE_USER")
							.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	*/
}
