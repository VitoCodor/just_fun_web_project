package com.vitoboy.funweb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: vito
 * @Date: 2020/10/13 17:06
 * @Version: 1.0
 */
@ConfigurationProperties(prefix = "appconfig")
public class AppConfig {

    public static String username;

    public static String password;

    public static String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        AppConfig.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        AppConfig.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        AppConfig.email = email;
    }
}
