package com.hospital.norvic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "staff_info")
@Getter
@Setter
public class StaffInfo extends UserInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_info_gen")
    @SequenceGenerator(name = "staff_info_gen", sequenceName = "staff_info_seq", allocationSize = 1)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info", foreignKey = @ForeignKey(name = "fk_staff_info_user_info"),
            referencedColumnName = "id")
    private UserInfo userInfo;

    private String photo;
}