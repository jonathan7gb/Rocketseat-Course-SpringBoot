package com.weg.rocketseatcourse.domain.enums;

import com.weg.rocketseatcourse.domain.entity.User;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    private String getRole(){
        return role;
    }
}
