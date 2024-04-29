package com.tuvarna.geo.config.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tuvarna.geo.service.security.DefaultUserDetailsService;
import com.tuvarna.geo.service.security.JWTTokenProvider;
import com.tuvarna.geo.service.security.JwtAuthenticationEntryPoint;
import com.tuvarna.geo.service.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final DefaultUserDetailsService defaultUserDetailsService;

    @Autowired
    protected SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
            DefaultUserDetailsService defaultUserDetailsService) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.defaultUserDetailsService = defaultUserDetailsService;
    }

    @Bean
    protected JwtAuthenticationFilter jwtAuthenticationFilter(JWTTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(jwtTokenProvider, defaultUserDetailsService);
    }

    @SuppressWarnings({ "removal" })
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
            JwtAuthenticationFilter authenticationFilter)
            throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .addFilterBefore(authenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/auth/**")
                .permitAll()
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.logout(logout -> logout.logoutUrl("/auth/logout")
                .invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    protected BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManagerBean() throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(defaultUserDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return new ProviderManager(Collections.singletonList(authProvider));
    }

}