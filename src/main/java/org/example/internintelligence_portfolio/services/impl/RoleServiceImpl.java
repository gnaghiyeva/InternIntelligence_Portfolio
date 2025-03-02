package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.roledto.RoleDto;
import org.example.internintelligence_portfolio.models.Role;
import org.example.internintelligence_portfolio.repositories.RoleRepository;
import org.example.internintelligence_portfolio.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> result = roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return result;
    }
}
