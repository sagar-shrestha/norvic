package com.hospital.norvic.entity;

import com.hospital.norvic.common.entity.UserDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "organization_info")
@Getter
@Setter
public class OrganizationInfo extends UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_info_gen")
    @SequenceGenerator(name = "organization_info_gen", sequenceName = "organization_info_seq", allocationSize = 1)
    private Integer Id;

    private String name;

    private String address;

    private String panOrVat;

    private String president;

    private String charPerson;

    private String dean;

    private String Slogan;
}
