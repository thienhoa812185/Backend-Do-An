package com.example.clinic.service.impl;


import com.example.clinic.entity.Role;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.repository.RoleRepository;
import com.example.clinic.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {


    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByRoleName(RolesEnum roleName) {
        return roleRepository.findByName(roleName);
    }
}
