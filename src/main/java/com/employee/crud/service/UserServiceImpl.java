package com.employee.crud.service;

import com.employee.crud.config.LoginConfig;
import com.employee.crud.config.LoginMessage;
import com.employee.crud.entity.User;
import com.employee.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        User user1 = new User(
                user.getUser_id(),
                user.getUserName(),
                user.getEmail(),
                this.passwordEncoder.encode(user.getPassword())
        );

        return userRepo.save(user1);
    }

    @Override
    public LoginMessage loginUser(LoginConfig loginConfig) {
        User user = userRepo.findByEmail(loginConfig.getEmail());
        if (user != null) {
            String password = loginConfig.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> model1 = userRepo.findOneByEmailAndPassword(loginConfig.getEmail(), encodedPassword);
                if (model1.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("Password Not Match", false);
            }
        } else {
            return new LoginMessage("Email does not exist", false);
        }
    }
}