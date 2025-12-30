package com.example.service;

import java.util.List;
import com.example.model.User;

public interface UserService {
    void save(User user);
    List<User> list();
    User findById(Long id);
    void delete(Long id);
}
