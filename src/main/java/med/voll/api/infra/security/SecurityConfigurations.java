package med.voll.api.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigurations {
 public DefaultSecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
  return httpSecurity
   .authorizeRequests()
   .antMatchers("/auth").permitAll()
   .antMatchers("/h2-console/**").permitAll()
   .anyRequest().authenticated()
   .and().csrf().disable()
   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
   .and().addFilterBefore(new AuthenticationByTokenFilter(), UsernamePasswordAuthenticationFilter.class)
   .headers().frameOptions().disable()
   .and().build();
 }
}