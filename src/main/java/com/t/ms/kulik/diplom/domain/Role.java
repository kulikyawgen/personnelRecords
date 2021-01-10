package com.t.ms.kulik.diplom.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, TUTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
