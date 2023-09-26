package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityConfigurations extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    // Obtener el token del header
    var authHeader = request.getHeader("Authorization");
    if (authHeader != null) {
      var token = authHeader.replace("Bearer ", "");
      var userName = tokenService.getSubject(token); // extract username
      if (userName != null) {
        // Token valido
        var user = userRepository.findByLogin(userName);
        var authentication = new UsernamePasswordAuthenticationToken(user, null,
            user.getAuthorities()); // Forzamos un inicio de sesion
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }
}
