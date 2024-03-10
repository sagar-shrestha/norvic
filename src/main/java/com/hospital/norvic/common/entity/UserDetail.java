package com.hospital.norvic.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements Serializable {

    private LocalDateTime createdIn;

    private String createdBy;

    private LocalDateTime modifiedIn;

    private String modifiedBy;

}
