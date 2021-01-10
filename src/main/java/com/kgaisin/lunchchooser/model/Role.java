package com.kgaisin.lunchchooser.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Kirill Gaisin
 */

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
