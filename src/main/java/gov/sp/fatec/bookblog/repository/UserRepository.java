package gov.sp.fatec.bookblog.repository;

import gov.sp.fatec.bookblog.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(final String username);
}
