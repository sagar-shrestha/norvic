package com.hospital.norvic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hospital.norvic.common.constant.Department;
import com.hospital.norvic.common.constant.Designation;
import com.hospital.norvic.common.constant.Roles;
import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nurse_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurseDetail extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nurse_detail_gen")
    @SequenceGenerator(name = "nurse_detail_gen", sequenceName = "nurse_detail_seq", allocationSize = 1)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nurse_info")
    private NurseInfo nurseInfo;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @ManyToOne
    @JoinColumn(name = "supervisor_info")
    @JsonBackReference
    private DoctorDetail supervisorInfo;

    private boolean newJoinee = false;

    private boolean transferred = false;

    private String lastBranch;
}