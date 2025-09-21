package com.hospital.controller;


//normal code no security:::::
//@RestController
//@RequestMapping("/api/user")
//@CrossOrigin(origins = "*")
//public class UserController {
//	
//  @Autowired
//  private UserDAO userDAO;
//  
//
//  @PostMapping("/login")
//  public ResponseEntity<?> login(@RequestBody User user) {
//      User matchedUser = userDAO.getByUsernameAndPassword(user.getUsername(), user.getPassword(), user.getUserRole());
//      if (matchedUser != null) {
//          Map<String, Object> response = new HashMap<>();
//          response.put("id", matchedUser.getId());
//          response.put("username", matchedUser.getUsername());
//          response.put("name", matchedUser.getName());
//          response.put("role", matchedUser.getUserRole());
//          response.put("imageUrl", matchedUser.getImageUrl());
//          return ResponseEntity.ok(response);
//      } else {
//          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//      }
//  }
//
//  @PostMapping("/register")
//  public User register(@RequestBody User user) {
//      return userDAO.save(user);
//  }
//
//  @PostMapping("/upload-image/{id}")
//  public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
//      try {
//          String folder = "uploads/";
//          String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//          Path filePath = Paths.get(folder + fileName);
//          Files.createDirectories(filePath.getParent());
//          Files.write(filePath, file.getBytes());
//
//          User user = userDAO.getUserById(id);
//          user.setImageUrl(fileName);
//          userDAO.save(user);
//
//          return ResponseEntity.ok("Image uploaded successfully");
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
//      }
//  }
//  
//  @GetMapping("/username/{username}")
//  public ResponseEntity<User> getByUsername(@PathVariable String username) {
//      User user = userDAO.getUserByUsername(username);
//      if (user != null) return ResponseEntity.ok(user);
//      else return ResponseEntity.notFound().build();
//  }
//
//  @GetMapping("/register")
//  public List<User> getAll() {
//      return userDAO.getAll();
//  }
//
//  @GetMapping("/register/{id}")
//  public ResponseEntity<User> getById(@PathVariable Long id) {
//      User user = userDAO.getUserById(id);
//      return ResponseEntity.ok(user);
//  }
//}
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.dao.UserDAO;
import com.hospital.model.User;

// //spring security code:::::
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔐 Login with Spring Security
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws AuthenticationException {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
		        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User matchedUser = userDAO.getUserByUsername(user.getUsername());

		if (matchedUser != null) {
		    Map<String, Object> response = new HashMap<>();
		    response.put("id", matchedUser.getId());
		    response.put("username", matchedUser.getUsername());
		    response.put("name", matchedUser.getName());
		    response.put("role", matchedUser.getUserRole());
		    response.put("imageUrl", matchedUser.getImageUrl());
		    return ResponseEntity.ok(response);
		} else {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		}
    }

    // 🔐 Register (password encode করে save)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userDAO.getUserByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ encode password
        User savedUser = userDAO.save(user);

        return ResponseEntity.ok(savedUser);
    }

    // 🔐 Upload Image
    @PostMapping("/upload-image/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        try {
            String folder = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(folder + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            User user = userDAO.getUserById(id);
            user.setImageUrl(fileName);
            userDAO.save(user);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
        }
    }

    // 🔐 Get User by Username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User user = userDAO.getUserByUsername(username);
        if (user != null) return ResponseEntity.ok(user);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/register")
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @GetMapping("/register/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userDAO.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
