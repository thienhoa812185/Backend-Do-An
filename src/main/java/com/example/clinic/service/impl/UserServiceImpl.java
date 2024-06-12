package com.example.clinic.service.impl;

import com.example.clinic.dto.ChangePasswordDTO;
import com.example.clinic.dto.RegisterDTO;
import com.example.clinic.dto.RoleResponse;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.repository.UserRepository;
import com.example.clinic.service.RoleService;
import com.example.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean createAccountForDoctor(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return false;
        } else {
            User user = User.builder()
                    .username(registerDTO.getUsername())
                    .password(passwordEncoder.encode((registerDTO.getPassword())))
                    .email(registerDTO.getEmail())
                    .build();

            Role roleDoctor = roleService.getRoleByRoleName(RolesEnum.DOCTOR);
            Role roleUser = roleService.getRoleByRoleName(RolesEnum.USER);
            Set<Role> roles = new HashSet<>();
            roles.add(roleDoctor);
            roles.add(roleUser);
            user.setRoles(roles);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existedByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean deleteUserAccount(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Role> getRoleByUserName(String username) {
        List<Role> roles = userRepository.findRolesByUsername(username);
        return roles;
    }

    @Override
    public void resetUserPassword(String username) {
        User user = getUserByUsername(username);
        user.setPassword(passwordEncoder.encode("12345678"));
        userRepository.save(user);
    }

    @Override
    public Boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        User user = getUserByUsername(changePasswordDTO.getUsername());
        if (!passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);
        return true;
    }

}
