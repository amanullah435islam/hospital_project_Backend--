package com.hospital.user.service;

import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.role.entity.Role;
import com.hospital.role.entity.RoleName;
import com.hospital.role.repository.RoleRepository;
import com.hospital.user.dto.UserCreateRequest;
import com.hospital.user.dto.UserResponse;
import com.hospital.user.entity.User;
import com.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByUsername(
                request.getUsername())) {

            throw new DuplicateResourceException(
                    "Username already exists"
            );
        }

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());

        Set<Role> roles = resolveRoles(
                request.getRoles()
        );

        user.setRoles(roles);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );

        return mapToResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Set<Role> resolveRoles(
            Set<String> roleNames
    ) {

        if (roleNames == null ||
                roleNames.isEmpty()) {

            Role defaultRole =
                    roleRepository
                            .findByName(RoleName.PATIENT)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Default role not found"
                                    )
                            );

            return new HashSet<>(
                    Set.of(defaultRole)
            );
        }

        return roleNames.stream()
                .map(String::toUpperCase)
                .map(RoleName::valueOf)
                .map(roleName ->
                        roleRepository
                                .findByName(roleName)
                                .orElseThrow(() ->
                                        new ResourceNotFoundException(
                                                "Role not found: "
                                                        + roleName
                                        )
                                )
                )
                .collect(Collectors.toSet());
    }

    private UserResponse mapToResponse(User user) {

        Set<String> roles = user.getRoles()
                .stream()
                .map(role ->
                        role.getName().name()
                )
                .collect(Collectors.toSet());

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .status(user.getStatus())
                .enabled(user.isEnabled())
                .roles(roles)
                .build();
    }
}
