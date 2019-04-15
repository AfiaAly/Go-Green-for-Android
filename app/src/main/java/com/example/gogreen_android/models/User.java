package com.example.gogreen_android.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@AllArgsConstructor

public class User implements Serializable {
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private boolean success;


}
