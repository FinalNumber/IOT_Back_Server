package com.example.iot_project_backserver.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // 필요시 CSRF 보호를 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/idcheck").permitAll()  // `/idcheck` 엔드포인트는 인증 없이 접근 가능
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/callvolunteer").permitAll()
                        .requestMatchers("/allvolunteercall").permitAll()
                        .requestMatchers("/patientvolunteercall").permitAll()
                        .requestMatchers("/volunteerassignment").permitAll()
                        .requestMatchers("/volunteercomplete").permitAll()
                        .requestMatchers("/assignmentcancel").permitAll()
                        .requestMatchers("/volunteercallmodify").permitAll()
                        .requestMatchers("/volunteercalldelete").permitAll()
                        .requestMatchers("/searchpatient").permitAll()
                        .requestMatchers("/assignmentpatient").permitAll()
                        .requestMatchers("/loadpatient").permitAll()
                        .requestMatchers("/deletepatient").permitAll()
                        .requestMatchers("/loadmeasure").permitAll()
                        .requestMatchers("/modifymeasure").permitAll()
                        .requestMatchers("/volunteertime").permitAll()
                        .requestMatchers("/tokencheck").permitAll()
                        .anyRequest().authenticated()  // 그 외의 요청은 인증 필요
                )
                .formLogin().disable();  // 로그인 폼을 비활성화, 필요 시 설정 가능

        return http.build();
    }
}
