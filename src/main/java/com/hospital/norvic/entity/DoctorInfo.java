package com.hospital.norvic.entity;

import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor_info")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInfo extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_info_gen")
    @SequenceGenerator(name = "doctor_info_gen", allocationSize = 1, sequenceName = "doctor_info_seq")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info", foreignKey = @ForeignKey(name = "fk_doctor_info_user_info_id"),
            referencedColumnName = "id")
    private UserInfo userInfo;

    private String photo;
}