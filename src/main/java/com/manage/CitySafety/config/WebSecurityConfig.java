package com.manage.CitySafety.config;

import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager; 
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.manage.CitySafety.Entity.Supervisor;
import com.manage.CitySafety.Repository.SupervisorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	SupervisorRepository supervisorRepository;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .successHandler(customAuthenticationSuccessHandler())
            .failureUrl("/login?error=true")// Custom success handler
            .permitAll()
        )
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .headers(headers -> headers
                .cacheControl(cache -> cache.disable())  // Disable caching
            )
            .sessionManagement(session -> session
                .maximumSessions(1)
                .expiredUrl("/login?expired=true")
            );


    return http.build();
	}
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

	@Bean
	public UserDetailsService userDetailsService() {
		 return username -> {
		        // Hardcoded admin user
			 if ("admin".equals(username)) {
		            return User.withUsername("admin")
		                    .password("{noop}admin123")
		                    .roles("ADMIN")
		                    .build();
		        }

		        // Supervisor logic (example)
		        Supervisor supervisor = supervisorRepository.findByName(username);
		        if (supervisor == null) {
		            throw new UsernameNotFoundException("Supervisor not found");
		        }

		        String decodedPassword = PasswordUtil.decode(supervisor.getPassword());
		        return User.withUsername(supervisor.getName())
		                .password("{noop}" + decodedPassword)
		                .roles("SUPERVISOR")
		                .build();
		    };
		}
}
