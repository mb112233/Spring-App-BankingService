package utcn.labs.sd.bankingservice.domain.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.User;

public interface UserRepository extends  JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = ?1")
	Optional<User> getByUsername(String emp);

}
