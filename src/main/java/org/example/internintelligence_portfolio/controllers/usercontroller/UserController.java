package org.example.internintelligence_portfolio.controllers.usercontroller;

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

    // Admin yetkisi vermə
    @PostMapping("/grant-admin/{currentUserId}/{targetUserId}")
    public ResponseEntity<String> grantAdminRole(@PathVariable Long currentUserId, @PathVariable Long targetUserId) {
        try {
            boolean isGranted = userService.giveAdminRole(currentUserId, targetUserId);
            if (isGranted) {
                return ResponseEntity.ok("İstifadəçiyə admin yetkisi uğurla verildi.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin yetkisi verilə bilmədi.");
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu əməliyyatı icra etmək üçün yetkiniz yoxdur.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir xəta baş verdi: " + e.getMessage());
        }
    }
}
