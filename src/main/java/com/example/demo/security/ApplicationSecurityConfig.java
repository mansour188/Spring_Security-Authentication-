package com.example.demo.security;

import com.example.demo.auth.ApplicationUserDetailsService;
import com.example.demo.jwt.JwtTokenfilter;
import com.example.demo.jwt.UsernameAndPasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo.security.ApplicationPermession.COURSE_READ;
import static com.example.demo.security.ApplicationPermession.COURSE_WRITE;
import static com.example.demo.security.UserRole.*;

@EnableWebSecurity

@Configuration

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter     // we use @Bean s
 {
     @Autowired
     PasswordEncoder passwordEncoder;
     @Autowired
     ApplicationUserDetailsService userDetailsService;



     @Override

     public void configure(HttpSecurity http) throws Exception {
          http

                  .csrf().disable()
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
                  .addFilter(new UsernameAndPasswordAuthFilter(authenticationManager()))
                  .addFilterAfter(new JwtTokenfilter(),UsernameAndPasswordAuthFilter.class)

                 .authorizeHttpRequests()

                  .antMatchers("/","idex").permitAll()

                  .antMatchers("/api/**").hasRole(STUDENT.name())
                  .antMatchers(HttpMethod.POST,"/manegement/**")
                  .hasAuthority(COURSE_WRITE.getPermission())
                  .antMatchers(HttpMethod.PUT,"/manegement/**")
                  .hasAuthority(COURSE_WRITE.getPermission())
                  .antMatchers(HttpMethod.DELETE,"/manegement/**")
                  .hasAuthority(COURSE_WRITE.getPermission())
                  .antMatchers(HttpMethod.GET,"/manegement/**")
                  .hasAnyAuthority(COURSE_WRITE.getPermission(),COURSE_READ.getPermission())


                  .anyRequest().authenticated()
                                  ;





     }
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.authenticationProvider(daoAuthenticationProvider());
     }
     @Bean
     public DaoAuthenticationProvider daoAuthenticationProvider(){
         DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
         provider.setPasswordEncoder(passwordEncoder);
         provider.setUserDetailsService(userDetailsService);
         return provider;

     }


 }
