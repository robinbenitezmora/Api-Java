package med.voll.api.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")

public class AuthenticationController {
 @PostMapping
 public ResponseEntity authenticate(DataAuthenticateUser dataAuthenticateUser) {
  Object dataAuthenticatiionUser;
  UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
    ((Object) dataAuthenticatiionUser).login(), dataAuthenticateUser.password());
  AuthenticationController authenticationManager;
  authenticationManager.authenticate(token);
  return ResponseEntity.ok().build();
 }
}
