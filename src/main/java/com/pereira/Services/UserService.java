package com.pereira.Services;

import com.pereira.Entities.User;
import com.pereira.Entities.Role;
import com.pereira.Repository.UserRepository;
import com.pereira.Repository.RoleRepository;
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

    public User register(User user) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        // Assign the role (by default assigning ROLE_FARMER, you can customize this logic)
        Role role = roleRepository.findByName("ROLE_FARMER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);

        return userRepository.save(user);
    }
}
