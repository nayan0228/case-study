
  package com.nayan.search.security;
  
  import org.springframework.context.annotation.Bean; import
  org.springframework.security.config.annotation.authentication.builders.
  AuthenticationManagerBuilder; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; import
  org.springframework.security.config.annotation.web.configuration.
  WebSecurityConfigurerAdapter; import
  org.springframework.security.crypto.password.NoOpPasswordEncoder; import
  org.springframework.security.crypto.password.PasswordEncoder;
  
  @EnableWebSecurity 
  public class SecurityConfig extends
  WebSecurityConfigurerAdapter {
  
  @Override protected void configure(AuthenticationManagerBuilder auth) throws
  Exception { // Set your configuration on the auth object
  auth.inMemoryAuthentication()
  
  .withUser("admin") .password("admin123") .roles("ADMIN"); }
  
  
  @Bean public PasswordEncoder getPasswordEncoder() { return
  NoOpPasswordEncoder.getInstance(); }
  
  
  @Override protected void configure(HttpSecurity http) throws Exception {
  http.csrf().disable().authorizeRequests() .antMatchers("/admi").hasRole("ADMIN")
  .antMatchers("/").permitAll() .and().formLogin(); } }
 