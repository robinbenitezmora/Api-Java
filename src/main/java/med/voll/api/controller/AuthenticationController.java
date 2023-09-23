package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.User;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.DatosJWTToken;
import med.voll.api.infra.security.TokenService;

import javax.xml.crypto.Data;

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

public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticationUser dataAuthenticationUser) {
    Authentication token = new UsernamePasswordAuthenticationToken(dataAuthenticationUser.login(),
        dataAuthenticationUser.password());
    var userAuthenticated = authenticationManager.authenticate(token);
    var JWTtoken = TokenService.generateToken((User) userAuthenticated.getPrincipal());
    return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
  }
}
