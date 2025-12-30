package com.example.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.example.model.User;
import com.example.service.UserService;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{userService}")
    private UserService userService;

    private User newUser;
    private List<User> users;

    @PostConstruct
    public void init() {
        newUser = new User();
        loadUsers();
    }

    public void loadUsers() {
        users = userService.list();
    }

    public void save() {
        userService.save(newUser);
        newUser = new User(); // reset
        loadUsers(); // reload list
    }

    public void delete(Long id) {
        userService.delete(id);
        loadUsers();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
