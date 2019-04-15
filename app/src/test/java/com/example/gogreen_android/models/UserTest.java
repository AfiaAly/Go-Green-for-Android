package com.example.gogreen_android.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    User user = new User("Klaas", "password", false);

    @Test
    public void constructorNotNullTest() {
        assertNotNull(user);
    }

    @Test
    public void allArgsConstructorTest() {
        User user = new User("Klaas", "password", true);
        assertNotNull(user);
        assertEquals("Klaas", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(true, user.isSuccess());
    }

    @Test
    public void setUsernameTest() {
        user.setUsername("Dirk");
        assertEquals("Dirk", user.getUsername());
    }

    @Test
    public void setPasswordTest() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void setSuccessTest() {
        user.setSuccess(true);
        assertEquals(true, user.isSuccess());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("Klaas", user.getUsername());
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void isValidTest() {
        assertEquals(false, user.isSuccess());
    }

}