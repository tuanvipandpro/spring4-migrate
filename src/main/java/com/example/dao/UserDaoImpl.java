package com.example.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> list() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }
    
    @Override
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }
    
    @Override
    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
