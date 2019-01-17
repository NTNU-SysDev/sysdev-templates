package no.ntnu.sysdev.SpringBoot_JDBC_DEMO.service;

import no.ntnu.sysdev.SpringBoot_JDBC_DEMO.Entity.User;
import no.ntnu.sysdev.SpringBoot_JDBC_DEMO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    private User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean addUser(User user) {
        User foundUser = getUserByEmail(user.getEmail());
        if (null == foundUser) {
            userRepository.addUser(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(String email) {
        User userToDelete = getUserByEmail(email);
        if (null != userToDelete) {
            userRepository.delete(email);
            return true;
        } else {
            return false;
        }
    }
}
