package com.codewithmosh.store.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.codewithmosh.store.users.User;
import com.codewithmosh.store.users.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    
    public User getCurrentUser() {
         var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }
}
