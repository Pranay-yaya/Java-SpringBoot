package com.example.Pranay.Authentication;

import com.example.Pranay.entity.User;
import com.example.Pranay.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= (User) userRepository.findByUsername(username);

        return new CustomUserDetails(user);
    }
}
