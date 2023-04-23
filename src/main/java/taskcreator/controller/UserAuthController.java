package taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import taskcreator.entites.LoginRequest;
import taskcreator.entites.UserAccount;
import taskcreator.service.UserAccountService;
import taskcreator.util.HashUtils;

import java.net.URI;

@RestController
public class UserAuthController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/taskcreator/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        UserAccount userAccount = userAccountService.findById(loginRequest.getEmail());

        HashUtils hashUtils = new HashUtils();
        if (userAccount == null || !(userAccount.getPassword().equals((hashUtils.sha256(loginRequest.getPassword()))))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/taskcreator/auth/signin")
    public ResponseEntity<?> signup(@RequestBody UserAccount userAccount) {

        UserAccount existingUser = userAccountService.findById(userAccount.getEmail());

        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        userAccountService.save(userAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");

    }
}
