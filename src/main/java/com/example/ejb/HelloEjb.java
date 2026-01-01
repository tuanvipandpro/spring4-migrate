package com.example.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloEjb {

    public String sayHello(String name) {
        return "Hello " + name + " from EJB!";
    }
}
