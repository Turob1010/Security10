package com.Oydin.Security.Service;


import com.Oydin.Security.Entity.Role;
import com.Oydin.Security.Entity.User;
import com.Oydin.Security.Repository.RoleRepository;
import com.Oydin.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public User create(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            Role newAdminRole = new Role();
            newAdminRole.setRole("ADMIN");
            roleRepository.save(newAdminRole);
        }

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            Role newUserRole = new Role();
            newUserRole.setRole("USER");
            roleRepository.save(newUserRole);
        }
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        user.setRoles(new HashSet<>(Arrays.asList(adminRole)));
        return userRepository.save(user);


    }

    public Boolean checkUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }


    }

