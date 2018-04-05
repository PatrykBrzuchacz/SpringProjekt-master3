package Main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
	User getUserById(Integer id);
	User findByEmail(String email);
	
}