package com.ceng.shared;

public interface Login<T> {
    Boolean isLoggedIn();
    T logout();

    void login(String username, String password);
}
