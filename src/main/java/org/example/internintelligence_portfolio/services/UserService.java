package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.authdtos.LoginDto;
import org.example.internintelligence_portfolio.dtos.authdtos.RegisterDto;

public interface UserService {
    boolean registerUser(RegisterDto registerDto);
    boolean giveAdminRole(Long currentUserId, Long targetUserId);
    boolean login(LoginDto loginDto);
}
