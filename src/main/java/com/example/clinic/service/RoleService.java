package com.example.clinic.service;

import com.example.clinic.entity.Role;
import com.example.clinic.enums.RolesEnum;

public interface RoleService {

    Role getRoleByRoleName(RolesEnum roleName);

}
