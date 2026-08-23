package com.hospital.user.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.doctor.entity.Doctor;
import com.hospital.enums.UserStatus;
import com.hospital.patient.entity.Patient;
import com.hospital.role.entity.Role;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {           //no duplicate use for uniqueConstraints
                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_user_username", columnNames = "username")
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private boolean enabled;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Doctor doctor;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Patient patient;
}