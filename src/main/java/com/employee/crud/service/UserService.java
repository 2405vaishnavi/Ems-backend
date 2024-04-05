package com.employee.crud.service;

import com.employee.crud.config.LoginConfig;
import com.employee.crud.config.LoginMessage;
import com.employee.crud.entity.User;

public interface UserService {
    User addUser(User user);
    LoginMessage loginUser(LoginConfig loginConfig);
}
