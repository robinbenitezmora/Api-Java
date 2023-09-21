package med.voll.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")

public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
    Authentication token = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.login(),
        dataAuthenticationUser.password());
    authenticationManager.authenticate(token);
    return ResponseEntity.ok().build();
  }
}
