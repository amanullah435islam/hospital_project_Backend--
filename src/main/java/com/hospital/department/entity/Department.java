package com.hospital.department.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "departments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_department_name",
                        columnNames = "name"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Department extends BaseEntity {

    @Column(
            nullable = false,
            length = 100
    )
    private String name;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private DepartmentStatus status = DepartmentStatus.ACTIVE;
}



//@Entity
//@Table(name = "departments")
//public class Department extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true, length = 100)
//    private String name;
//
//    private String description;
//
//    @Enumerated(EnumType.STRING)
//    private DepartmentStatus status;
//
//    @OneToMany(mappedBy = "department")
//    private Set<Doctor> doctors = new HashSet<>();
//}
