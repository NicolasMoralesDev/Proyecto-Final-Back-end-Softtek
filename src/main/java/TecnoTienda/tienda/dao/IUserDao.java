package TecnoTienda.tienda.dao;

import TecnoTienda.tienda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * Method for find a user by email.
     * @param email String, email of the user.
     * @return Optional<User>, the user if it exists.
     */
    Optional<User> findByEmail(String email);

    /**
     * Method for check if a user exists by email.
     * @param email String, email of the user.
     * @return boolean, true if the user exists.
     */
    boolean existsByEmail(String email);
}
