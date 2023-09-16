package med.voll.api.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

 Object findByUsername(String username);

}
