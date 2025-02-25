package com.hashedin.huspark.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hashedin.huspark.Entity.User;
import com.hashedin.huspark.Service.UserService;
import com.hashedin.huspark.Security.JwtUtil;
import org.springframework.context.annotation.Lazy;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(@Lazy AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user using username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Extract authenticated user from authentication object
            User user = (User) authentication.getPrincipal();

            // Generate a JWT token for the user
            String token = jwtUtil.generateToken(user);

            // Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            // Handle invalid credentials exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    // Signup Endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupRequest signupRequest) {
        // Check if user already exists
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
        }

        // Generate a userID or get it from some service
        String generatedUserID = generateUserID();

        // Create the user using the constructor with required parameters
        User user = new User(
                generatedUserID, // You can generate this dynamically
                passwordEncoder.encode(signupRequest.getPassword()), // encrypt password
                "USER", // Default role
                null, // Address can be added if required
                null, // Gender can be added if required
                null, // Date of birth can be added if required
                null, // Avatar URL can be added if required
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getUsername()
        );

        // Save user to the database
        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    private String generateUserID() {
        // Implement your logic to generate a userID here
        return "user-" + System.currentTimeMillis();
    }

    // Request body class for login
    public static class LoginRequest {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Request body class for signup
    public static class UserSignupRequest {
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String email;

        // Getters and Setters
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // Response class for authentication response with token
    public static class AuthResponse {
        private String token;

        // Constructor
        public AuthResponse(String token) {
            this.token = token;
        }

        // Getter for token
        public String getToken() {
            return token;
        }
    }
}
