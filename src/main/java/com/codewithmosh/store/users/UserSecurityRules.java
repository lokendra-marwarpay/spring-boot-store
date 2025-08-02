package com.codewithmosh.store.users;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

import com.codewithmosh.store.common.SecurityRules;

@Component
public class UserSecurityRules implements SecurityRules {
    @Override
    public void config(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        registry.requestMatchers(HttpMethod.POST, "/users").permitAll();
    }
}
