package com.codeviewandtalk.library.management.service;

import com.codeviewandtalk.library.management.model.User;
import com.codeviewandtalk.library.management.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserDetailService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.get().getRoles().forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getName()));
        });
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);

    }
}
