package com.micronil.web.service;

import com.micronil.web.entity.User;
import com.micronil.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;
    public void save(User user) {
        userRepository.save(user);
    }

    public User findUser(String username, String password) {
        return userRepository.findByUserNameAndPassword(username,password);
    }

    public User findUserById(Long userId) {
        Optional<User> opRole = userRepository.findById(userId);
        return opRole.isPresent() ? opRole.get() : null;
    }
}
