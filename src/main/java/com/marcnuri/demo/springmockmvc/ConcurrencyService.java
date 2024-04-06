package com.marcnuri.demo.springmockmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class ConcurrencyService {


    public static Vector<User> mockDatabase = new Vector();

    @Autowired
    UserManager userManager;

    @Autowired
    User u;
    public void test(){
        User userFromAop = userManager.getUserOrDefault();

        // simulate DB action
        mockDatabase.add(userFromAop);
    }
}
