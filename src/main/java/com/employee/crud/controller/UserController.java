package com.employee.crud.controller;

import com.employee.crud.config.LoginConfig;
import com.employee.crud.config.LoginMessage;
import com.employee.crud.entity.User;
import com.employee.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/auth/save")
    public User saveEmployee(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginMessage> loginUser(@RequestBody LoginConfig loginConfig) {
        try {
            LoginMessage loginMessage = userService.loginUser(loginConfig);
            return ResponseEntity.ok(loginMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginMessage("An error occurred", false));
        }
    }

}
