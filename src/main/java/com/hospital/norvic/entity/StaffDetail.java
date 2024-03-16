package com.hospital.norvic.entity;

import com.hospital.norvic.common.constant.Department;
import com.hospital.norvic.common.constant.Designation;
import com.hospital.norvic.common.constant.Roles;
import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "staff_detail")
@Getter
@Setter
public class StaffDetail extends UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_detail_gen")
    @SequenceGenerator(name = "staff_detail_gen", sequenceName = "staff_detail_seq", allocationSize = 1)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "staff_info", foreignKey = @ForeignKey(name = "fk_staff_detail_staff_info_id"), referencedColumnName = "id")
    private StaffInfo staffInfo;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "supervisor_info", foreignKey = @ForeignKey(name = "id"), referencedColumnName = "id")
    private UserInfo supervisorInfo;

    @Enumerated(EnumType.STRING)
    private Roles roles;
}