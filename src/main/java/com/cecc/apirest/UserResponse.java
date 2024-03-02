package com.cecc.apirest;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;
    
    private Boolean isActive;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public LocalDateTime getModified() {
        return modified;
    }
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
    public LocalDateTime getLast_login() {
        return last_login;
    }
    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}


    