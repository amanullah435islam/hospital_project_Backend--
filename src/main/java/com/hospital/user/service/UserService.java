package com.hospital.user.service;

import com.hospital.user.dto.UserCreateRequest;
import com.hospital.user.dto.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}