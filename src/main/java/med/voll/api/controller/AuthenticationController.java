package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.DataAuthenticationUser;
import med.voll.api.domain.users.User;
import med.voll.api.infra.security.DataJWTToken;
import med.voll.api.infra.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Authentication of users")

public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<DataJWTToken> authenticateUser(
      @RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
    Authentication autoToken = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.login(),
        dataAuthenticationUser.password());
    var userAuthenticated = authenticationManager.authenticate(autoToken);
    var JWTtoken = tokenService.generateToken((User) userAuthenticated.getPrincipal());
    return ResponseEntity.ok(new DataJWTToken((String) JWTtoken));
  }
}
