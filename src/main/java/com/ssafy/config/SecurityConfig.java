package com.ssafy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.jwt.util.JwtAuthenticationFilter;
import com.ssafy.jwt.util.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
 
    private final JwtTokenProvider jwtTokenProvider;
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/test").hasRole("USER") 
                .antMatchers("/**").permitAll() // 개발용 전체 임시 허용. 모든 권한(role)에 대해 접근을 허용한다. 필터는 적용된다. 
//                .antMatchers("/housedeal/**").permitAll()	// 아파트 조회용
//                .antMatchers("/user/login", "/user/logout/*", "/user/refresh").permitAll();	// jwt 발급 요청 로그인 및 재발급 요청
//                .anyRequest().authenticated();
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}