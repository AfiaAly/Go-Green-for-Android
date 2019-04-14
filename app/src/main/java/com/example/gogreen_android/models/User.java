package com.example.gogreen_android.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString

public class User implements Serializable {
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private boolean success;

    public User(String username, String password, boolean success) {
        this.username = username;
        this.password = password;
        this.success = success;
    }

}
