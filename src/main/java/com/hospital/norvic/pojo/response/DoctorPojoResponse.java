package com.hospital.norvic.pojo.response;

import com.hospital.norvic.common.constant.Department;
import com.hospital.norvic.common.constant.Designation;
import com.hospital.norvic.common.constant.Gender;
import com.hospital.norvic.common.constant.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPojoResponse {

    private Integer id;

    @NotBlank(message = "First Name cannot be null.")
    @Size(min = 2, max = 20, message = "First Name must be between 2 to 20 characters.")
    private String firstName;

    @Size(min = 2, max = 20, message = "Middle Name must be between 2 to 20 characters.")
    private String middleName;

    @NotBlank(message = "Last Name cannot be null.")
    @Size(min = 2, max = 20, message = "Last Name must be between 2 to 20 characters.")
    private String lastName;

    @NotNull(message = "Please specify gender.")
    private Gender gender;

    @NotBlank(message = "Permanent Address cannot be null.")
    @Size(min = 3, max = 60, message = "Permanent Address must be between 3 to 60 characters.")
    private String permanentAddress;

    @NotBlank(message = "Temporary Address cannot be null.")
    @Size(min = 3, max = 60, message = "Temporary Address must be between 3 to 60 characters.")
    private String temporaryAddress;

    @NotBlank(message = "Current Address cannot be null.")
    @Size(min = 3, max = 60, message = "Address must be between 3 to 60 characters.")
    private String currentAddress;

    @Email(message = "Email should be valid.")
    private String email;

    @Email(message = "Email should be valid.")
    private String altEmail;

    @Size(min = 8, max = 8, message = "Phone number cannot be more or less than 10 digit.")
    private String phone;

    @Size(min = 8, max = 8, message = "Phone number cannot be more or less than 10 digit.")
    private String altPhone;

    @NotBlank(message = "Mobile number cannot be null.")
    @Size(min = 10, max = 10, message = "Mobile number cannot be more or less than 10 digit.")
    private String mobile;

    @Size(min = 10, max = 10, message = "Mobile number cannot be more or less than 10 digit.")
    private String altMobile;

    private Resource photo;

    private Department department;

    private Designation designation;

    private Roles roles;

    private Integer supervisorInfo;

    private boolean newJoinee;

    private boolean transferred;

    private String lastBranch;
}
