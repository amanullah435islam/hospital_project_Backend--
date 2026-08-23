package com.hospital.user.entity;


import com.hospital.common.entity.BaseEntity;
import com.hospital.role.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_username",
                        columnNames = "username"
                ),
                @UniqueConstraint(
                        name = "uk_user_email",
                        columnNames = "email"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(
            nullable = false,
            length = 50
    )
    private String username;

    @Column(
            nullable = false,
            length = 150
    )
    private String email;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 100
    )
    private String lastName;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private UserStatus status = UserStatus.ACTIVE;

    @Column(nullable = false)
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id"
            )
    )
    private Set<Role> roles = new HashSet<>();
}



//@Entity
//@Table(
//        name = "users",
//        uniqueConstraints = {           //no duplicate use for uniqueConstraints
//                @UniqueConstraint(name = "uk_user_email", columnNames = "email"),
//                @UniqueConstraint(name = "uk_user_username", columnNames = "username")
//        }
//)
//public class User extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Role> roles = new HashSet<>();
//
//    @Column(nullable = false, length = 50)
//    private String username;
//
//    @Column(nullable = false, length = 150)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String phone;
//
//    @Enumerated(EnumType.STRING)
//    private UserStatus status;
//
//    private boolean enabled;
//
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Doctor doctor;
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Patient patient;
//}