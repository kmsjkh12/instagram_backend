package com.example.numble_insta.config;

import com.example.numble_insta.jwt.JwtAccessDeniedHandler;
import com.example.numble_insta.jwt.JwtAuthenticationEntryPoint;
import com.example.numble_insta.jwt.JwtSecurityConfig;
import com.example.numble_insta.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/**/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**/signup")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**/profile")).permitAll()

                .anyRequest().authenticated() //위의 세개를 제외하고는 모든 요청은 인증을 거쳐야함

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();


    }


}
