package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.UserDao;
import com.example.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }
    
    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
    
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
