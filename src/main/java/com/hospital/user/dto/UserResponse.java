package com.hospital.user.dto;

import com.hospital.user.entity.UserStatus;
import lombok.Builder;
import lombok.Getter;
import java.util.Set;

@Getter
@Builder
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private UserStatus status;

    private boolean enabled;

    private Set<String> roles;
}