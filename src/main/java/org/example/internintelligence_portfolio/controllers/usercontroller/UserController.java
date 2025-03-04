package org.example.internintelligence_portfolio.controllers.usercontroller;

import org.example.internintelligence_portfolio.dtos.authdtos.LoginDto;
import org.example.internintelligence_portfolio.dtos.authdtos.RegisterDto;
import org.example.internintelligence_portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute RegisterDto registerDto){
        try {
            boolean isRegistered = userService.registerUser(registerDto);
            if(!isRegistered){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
            }
            return ResponseEntity.status(HttpStatus.OK).body("User registered succesfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured "+e.getMessage());
        }
    }

    @PostMapping("/grant-admin/{currentUserId}/{targetUserId}")
    public ResponseEntity<String> grantAdminRole(@PathVariable Long currentUserId, @PathVariable Long targetUserId) {
        try {
            boolean isGranted = userService.giveAdminRole(currentUserId, targetUserId);
            if (isGranted) {
                return ResponseEntity.ok("The user has been successfully granted admin privileges.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin permission could not be granted..");
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this operation.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> login(@ModelAttribute LoginDto loginDto) {
        boolean isLogin = userService.login(loginDto);
        if(!isLogin){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. Email or password is incorrect.");
        }
        return ResponseEntity.ok("Login completed successfully.");
    }
}
