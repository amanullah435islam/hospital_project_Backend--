package com.hospital.service;

//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.hospital.dao.UserDAO;
//import com.hospital.model.User;
//
//
//normal code no security::::::
//@Service
//public class UserService implements UserDetailsService {
//
//    private final UserDAO userDAO;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserService(UserDAO userDAO, @Lazy PasswordEncoder passwordEncoder) {
//        this.userDAO = userDAO;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    // ✅ নতুন ইউজার save করো (password encode করে)
//    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userDAO.save(user);
//    }
//
//    // ✅ username দিয়ে ইউজার খোঁজা
//    public Optional<User> findByUsername(String username) {
//        return Optional.ofNullable(userDAO.getUserByUsername(username));
//    }
//
//    // ✅ সব ইউজার লিস্ট পাওয়া
//    public List<User> getAllUsers() {
//        return userDAO.getAll();
//    }
//
//    // ✅ id দিয়ে ইউজার খোঁজা
//    public Optional<User> getUserById(long id) {
//        return Optional.ofNullable(userDAO.getUserById(id));
//    }
//
//    // ✅ Login এর সময় Spring Security এই method call করবে
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userDAO.getUserByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found: " + username);
//        }
//
//        // 🔹 Role থেকে authority map করা
//        String roleName = (user.getUserRole() != null) ? user.getUserRole().name() : "USER";
//
//        return User.withUsername(user.getUsername())
//                   .password(user.getPassword())
//                   .authorities(roleName) // DB থেকে role set
//                   .build();
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital.dao.UserDAO;
import com.hospital.enums.Role;
import com.hospital.model.User;

import java.util.List;
// //Spring security code::::::
@Service
public class UserService implements UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, @Lazy PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register user (password encode করে)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    // ✅ সব ইউজার দেখার জন্য
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    // ✅ Id দিয়ে ইউজার খোঁজা
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    // ✅ Username দিয়ে ইউজার খোঁজা
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    // ✅ Authentication এর সময় Spring Security এই মেথড কল করে
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("❌ User not found with username: " + username);
        }

        // Spring Security compatible user বানাচ্ছি
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())  // already encoded
                .authorities(user.getUserRole().name()) // DB থেকে Role use হচ্ছে
                .build();
    }

    // ✅ Custom login check (DAO method ব্যবহার করে)
    public User login(String username, String password, Role role) {
        return userDAO.getByUsernameAndPassword(username, password, role);
    }
}

