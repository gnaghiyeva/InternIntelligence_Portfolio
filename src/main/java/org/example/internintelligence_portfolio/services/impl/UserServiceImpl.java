package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.authdtos.RegisterDto;
import org.example.internintelligence_portfolio.models.Role;
import org.example.internintelligence_portfolio.models.User;
import org.example.internintelligence_portfolio.repositories.RoleRepository;
import org.example.internintelligence_portfolio.repositories.UserRepository;
import org.example.internintelligence_portfolio.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public boolean registerUser(RegisterDto registerDto) {
        User user = userRepository.findByEmail(registerDto.getEmail());
        if(user!=null){
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(registerDto.getPassword());
        Random random = new Random();
        String token = String.valueOf(random.nextInt(26,30));
        User newUser = modelMapper.map(registerDto, User.class);
        newUser.setFirstName(registerDto.getFirstname());
        newUser.setLastName(registerDto.getLastname());
        newUser.setPassword(hashPassword);
        newUser.setConfirmationToken(token);

        Role defaultRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("USER");
                    return roleRepository.save(newRole);
                });
        newUser.setRoles(List.of(defaultRole));

        userRepository.save(newUser);
        return true;
    }

    @Override
    public boolean giveAdminRole(Long currentUserId, Long targetUserId) {
        Optional<User> currentUser = userRepository.findById(currentUserId);
        if (currentUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User myCurrentUser = currentUser.get();
        if(!myCurrentUser.getId().equals(targetUserId)){
            throw new IllegalArgumentException("User and target user are not the same");
        }

        Optional<User> targetUser = userRepository.findById(targetUserId);
        if (targetUser.isEmpty()) {
            throw new IllegalArgumentException("Target user not found");
        }

        User myTargetUser = targetUser.get();
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Admin role not found!"));

        if (!myTargetUser.getRoles().contains(adminRole)) {
            myTargetUser.getRoles().add(adminRole);
            userRepository.save(myTargetUser);
            return true;
        }
        return false;
    }
}
