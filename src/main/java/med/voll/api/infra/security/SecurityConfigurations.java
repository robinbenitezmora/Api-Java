package med.voll.api.infra.security;

import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import static org.springframework.http.HttpMethod.POST;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
    @EnableWebSecurity
    public class SecurityConfigurations {

        @Autowired
        private SecurityFilter securityFilter;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import static org.springframework.http.HttpMethod.POST;

public class SecurityConfigurations {
  private static class SessionCreationPolicyConfigurer
      extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final SessionCreationPolicy sessionCreationPolicy;

    public SessionCreationPolicyConfigurer(SessionCreationPolicy sessionCreationPolicy) {
      this.sessionCreationPolicy = sessionCreationPolicy;
    }

    @Override
    public void configure(HttpSecurity http) {
      http.sessionManagement().sessionCreationPolicy(sessionCreationPolicy);
    }
  }

  private SessionCreationPolicyConfigurer sessionCreationPolicy(SessionCreationPolicy stateless) {
    return new SessionCreationPolicyConfigurer(stateless);
  }

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .sessionManagement(
            sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests(authorizeRequests -> authorizeRequests
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(WebSecurity webSecurity) throws Exception {
    return webSecurity
        .ignoring()
        .antMatchers(POST, "/login")
        .and()
        .build();
  }

}