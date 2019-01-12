package no.ntnu.sysdev.SpringBoot_Demo.repository;

import no.ntnu.sysdev.SpringBoot_Demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface extends the JpaRepository and gives us
 * the abstraction to significantly reduce the amount
 * of boilerplate code requires to implement data access layers
 * for various persistence stores
 *
 * So instead of writing queries ourselves, they are automatically
 * generated from the methods given below.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findAll();
}
