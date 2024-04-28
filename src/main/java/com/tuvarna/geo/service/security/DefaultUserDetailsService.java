package com.tuvarna.geo.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tuvarna.geo.repository.UserRepository;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.tuvarna.geo.entity.User user = userRepository.findByEmail(email);

        if (user != null) {
            return User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
