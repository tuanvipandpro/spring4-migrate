package com.example.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.service.UserService;
import com.example.model.User;
import java.util.List;

@Component
@WebService(serviceName = "UserSoapService")
public class CheckSoap {

    @Autowired
    private UserService userService;

    @WebMethod
    public String sayHello(String name) {
        return "Hello, " + name + " from SOAP!";
    }

    @WebMethod
    public List<User> getAllUsers() {
        return userService.list();
    }
}
