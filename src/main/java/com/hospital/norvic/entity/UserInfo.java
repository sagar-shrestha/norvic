package com.hospital.norvic.entity;

import com.hospital.norvic.common.constant.Gender;
import com.hospital.norvic.common.constant.UserType;
import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_info_gen")
    @SequenceGenerator(name = "user_info_gen", sequenceName = "user_info_seq", allocationSize = 1)
    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String permanentAddress;

    private String temporaryAddress;

    private String currentAddress;

    private String email;

    private String altEmail;

    private String phone;

    private String altPhone;

    private String mobile;

    private String altMobile;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}