package com.example.clinic.service;

import com.example.clinic.dto.ChangePasswordDTO;
import com.example.clinic.dto.RegisterDTO;
import com.example.clinic.dto.RoleResponse;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;

import java.util.List;

public interface UserService {

    List<User>getAllUsers();

    User save(User user);

    boolean createAccountForDoctor(RegisterDTO registerDTO);
    User getUserByUsername(String username);
    boolean existedByUsername(String username);

    boolean deleteUserAccount(String username);


    List<Role> getRoleByUserName(String username);

    void resetUserPassword(String username);

    Boolean changePassword(ChangePasswordDTO changePasswordDTO);


}
