package com.example.clinic.controller;


import com.example.clinic.dto.ChangePasswordDTO;
import com.example.clinic.dto.LoginDTO;
import com.example.clinic.dto.RegisterDTO;
import com.example.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adminAction")
public class AdminActionController {

    private final UserService userService;

    @Autowired
    public AdminActionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createAccountForDoctor")
    public ResponseEntity<?> createAccountForDoctor(@RequestBody RegisterDTO registerDTO) {
        boolean res = userService.createAccountForDoctor(registerDTO);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).body("Success!!!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please check inputtt");
        }
    }

    @DeleteMapping("/deleteUserAccount")
    public ResponseEntity<?> deleteUserAccount(@RequestParam String username) {
        boolean res = userService.deleteUserAccount(username);
        if (res) {
            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }

    @PutMapping("/resetUserPassword")
    public ResponseEntity<?> resetUserPassword(@RequestParam(value = "username", required = true) String username) {
        userService.resetUserPassword(username);
        return ResponseEntity.status(HttpStatus.OK).body("OKKK");
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        Boolean isChange = userService.changePassword(changePasswordDTO);
        if (isChange) {
            return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Current password is incorrect.");
    }
}
