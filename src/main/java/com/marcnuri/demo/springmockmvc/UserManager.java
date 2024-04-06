package com.marcnuri.demo.springmockmvc;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserManager {
    private ThreadLocal<User> sharedValue = new ThreadLocal<>();

    public User getUserOrDefault() {
        User value = sharedValue.get();
        if (value == null) {
            return new User(-1);
        }
        return value;
    }

    public void setUser(User value) {
        sharedValue.set(value);
    }
    public void unsetUser() {
        sharedValue.remove();
    }
}
