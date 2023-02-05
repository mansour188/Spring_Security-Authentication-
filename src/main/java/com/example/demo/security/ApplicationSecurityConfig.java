package com.example.demo.student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.demo.student.security.UserRole.*;

@EnableWebSecurity

@Configuration
public class ApplicationSecurityConfig    // we use @Bean s
 {
     @Autowired
     PasswordEncoder passwordEncoder;
     @Bean
     public SecurityFilterChain configure(HttpSecurity http) throws Exception {
          http

                 .authorizeHttpRequests(auth->{auth.requestMatchers("/*").permitAll() ;
                        auth.requestMatchers("/api/**")
                                .hasRole(STUDENT.name());
                        auth.anyRequest().authenticated();})


                 .httpBasic(Customizer.withDefaults()) ;
                return  http.build();





     }

    @Bean
     public UserDetailsService loadUserByusername(){
         UserDetails user1= User
                 .builder()
                 .username("mansour")
                 .password(passwordEncoder.encode("amani"))
                 .roles(STUDENT.name())
                 .build();

         UserDetails userAdmin=User
                 .builder()
                 .username("userAdmin")
                 .password(passwordEncoder.encode("mansour"))
                 .roles(ADMIN.name())
                 .build();
        UserDetails userAdmintraine=User
                .builder()
                .username("userAdmintraine")
                .password(passwordEncoder.encode("mansour"))
                .roles(ADMINTRAINE.name())
                .build();
         return new InMemoryUserDetailsManager(user1,userAdmintraine,userAdmin);
     }

 }
