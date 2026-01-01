package com.example.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.example.ejb.HelloEjb;
import com.example.model.User;
import com.example.service.UserService;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    // @ManagedProperty("#{userService}") // Removing JSF injection
    private UserService userService;

    // @EJB // Removing field injection to avoid JSF management issues
    private HelloEjb helloEjb;

    private User newUser;
    private List<User> users;
    private String ejbMessage;

    @PostConstruct
    public void init() {
        // Manual lookup for Spring Bean
        javax.servlet.ServletContext servletContext = (javax.servlet.ServletContext) javax.faces.context.FacesContext
                .getCurrentInstance().getExternalContext().getContext();
        org.springframework.web.context.WebApplicationContext springContext = org.springframework.web.context.support.WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        if (springContext != null) {
            userService = springContext.getBean(UserService.class);
        } else {
            System.err.println("Spring Context is null in UserBean!");
        }

        newUser = new User();
        // Only load if service was found
        if (userService != null) {
            loadUsers();
        }

        try {
            javax.naming.Context ctx = new javax.naming.InitialContext();
            // JBoss EJB 3.1 Lite JNDI convention for WAR deployment: java:module/BeanName
            helloEjb = (HelloEjb) ctx.lookup("java:module/HelloEjb");
            ejbMessage = helloEjb.sayHello("Admin");
        } catch (Exception e) {
            e.printStackTrace();
            ejbMessage = "EJB Lookup Failed: " + e.getMessage();
        }
    }

    public void loadUsers() {
        if (userService != null) {
            users = userService.list();
        }
    }

    public String save() {
        if (userService != null) {
            userService.save(newUser);
            newUser = new User(); // reset
            // Redirect to prevent duplicate submission on page refresh
            return "users?faces-redirect=true";
        }
        return null;
    }

    public String delete(Long id) {
        if (userService != null) {
            userService.delete(id);
            // Redirect to refresh the list clean
            return "users?faces-redirect=true";
        }
        return null;
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

    public String getEjbMessage() {
        return ejbMessage;
    }

    public void setEjbMessage(String ejbMessage) {
        this.ejbMessage = ejbMessage;
    }
}
