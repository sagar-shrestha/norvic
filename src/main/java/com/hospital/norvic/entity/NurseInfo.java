package com.hospital.norvic.entity;


import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "nurse_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurseInfo extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_info", foreignKey = @ForeignKey(name = "fk_nurse_info_user_info_id"),
            referencedColumnName = "id")
    private UserInfo userInfo;

    private String photo;
}