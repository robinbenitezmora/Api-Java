package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.domain.users.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

 @Autowired
 private UserRepository userRepository;

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  return (UserDetails) userRepository.findByUsername(username);
 }

}
