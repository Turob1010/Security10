package com.Oydin.Security.Service;


import com.Oydin.Security.Entity.User;
import com.Oydin.Security.Repository.RoleRepository;
import com.Oydin.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Boolean checkUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

//    @Bean
//    CommandLineRunner init(RoleRepository roleRepository) {
//
//        return args -> {
//
//            Role adminRole = roleRepository.findByrole("ADMIN");
//            if (adminRole == null) {
//                Role newAdminRole = new Role();
//                newAdminRole.setRole("ADMIN");
//                roleRepository.save(newAdminRole);
//            }
//
//            Role userRole = roleRepository.findByrole("USER");
//            if (userRole == null) {
//                Role newUserRole = new Role();
//                newUserRole.setRole("USER");
//                roleRepository.save(newUserRole);
//            }
//        };
    }

