package com.foodpurchasingsystem.foodpurchasingsystem.util;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.User;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.UserException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetLoggedUser {
    private final UserRepository userRepository;

    public GetLoggedUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userRepository.findByUsername(currentUser).orElseThrow(()-> new UserException("Username not found"));
        return user;
    }

}
