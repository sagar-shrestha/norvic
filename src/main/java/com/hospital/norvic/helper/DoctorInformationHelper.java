package com.hospital.norvic.helper;

import com.hospital.norvic.entity.DoctorDetail;
import com.hospital.norvic.entity.DoctorInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.pojo.response.DoctorPojoResponse;
import com.hospital.norvic.repository.DoctorDetailRepository;
import com.hospital.norvic.repository.DoctorInfoRepository;
import com.hospital.norvic.repository.UserRepository;
import com.hospital.norvic.util.GenericFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorInformationHelper {

    private final DoctorDetailRepository doctorDetailRepository;
    private final DoctorInfoRepository doctorInfoRepository;
    private final UserRepository userRepository;
    private final GenericFileUtil genericFileUtil;

    public DoctorPojoResponse getDoctorInformationById(Integer id) throws MalformedURLException {
        DoctorDetail doctorDetail = doctorDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor Detail cannot be found."));
        DoctorInfo doctorInfo = doctorInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor Info cannot found."));
        UserInfo userInfo = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Info is not available."));
        Resource image = genericFileUtil.getFile(doctorInfo.getPhoto());
        DoctorPojoResponse doctorPojoResponse = DoctorPojoResponse.builder()
                .id(doctorDetail.getId())
                .firstName(userInfo.getFirstName())
                .middleName(userInfo.getMiddleName())
                .lastName(userInfo.getLastName())
                .gender(userInfo.getGender())
                .permanentAddress(userInfo.getPermanentAddress())
                .temporaryAddress(userInfo.getTemporaryAddress())
                .currentAddress(userInfo.getCurrentAddress())
                .email(userInfo.getEmail())
                .altEmail(userInfo.getAltEmail())
                .phone(userInfo.getPhone())
                .altPhone(userInfo.getAltPhone())
                .mobile(userInfo.getMobile())
                .altMobile(userInfo.getAltMobile())
                .photo(image)
                .department(doctorDetail.getDepartment())
                .designation(doctorDetail.getDesignation())
                .roles(doctorDetail.getRoles())
                .supervisorInfo(doctorDetail.getSupervisorInfo().getId())
                .newJoinee(doctorDetail.isNewJoinee())
                .transferred(doctorDetail.isTransferred())
                .lastBranch(doctorDetail.getLastBranch())
                .build();
        return doctorPojoResponse;
    }

    public List<DoctorPojoResponse> getAllDoctorInformation() {
        List<DoctorPojoResponse> doctorPojoResponseList = new ArrayList<>();
        List<DoctorDetail> doctorDetailList = doctorDetailRepository.findAll();

        doctorDetailList.forEach(doctorDetail -> {
            DoctorInfo doctorInfo = doctorInfoRepository.findById(doctorDetail.getDoctorInfo().getId())
                    .orElseThrow(() -> new RuntimeException("Doctor Info cannot found."));
            UserInfo userInfo = userRepository.findById(doctorInfo.getUserInfo().getId())
                    .orElseThrow(() -> new RuntimeException("User Info is not available."));
            Resource image = null;
            try {
                image = genericFileUtil.getFile(doctorInfo.getPhoto());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            DoctorPojoResponse doctorPojoResponse = DoctorPojoResponse.builder()
                    .id(doctorDetail.getId())
                    .firstName(userInfo.getFirstName())
                    .middleName(userInfo.getMiddleName())
                    .lastName(userInfo.getLastName())
                    .gender(userInfo.getGender())
                    .permanentAddress(userInfo.getPermanentAddress())
                    .temporaryAddress(userInfo.getTemporaryAddress())
                    .currentAddress(userInfo.getCurrentAddress())
                    .email(userInfo.getEmail())
                    .altEmail(userInfo.getAltEmail())
                    .phone(userInfo.getPhone())
                    .altPhone(userInfo.getAltPhone())
                    .mobile(userInfo.getMobile())
                    .altMobile(userInfo.getAltMobile())
                    .photo(image)
                    .department(doctorDetail.getDepartment())
                    .designation(doctorDetail.getDesignation())
                    .roles(doctorDetail.getRoles())
                    .supervisorInfo(doctorDetail.getSupervisorInfo().getId())
                    .newJoinee(doctorDetail.isNewJoinee())
                    .transferred(doctorDetail.isTransferred())
                    .lastBranch(doctorDetail.getLastBranch())
                    .build();
            doctorPojoResponseList.add(doctorPojoResponse);
        });
        return doctorPojoResponseList;
    }
}
