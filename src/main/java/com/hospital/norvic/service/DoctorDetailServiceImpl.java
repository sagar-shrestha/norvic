package com.hospital.norvic.service;

import com.hospital.norvic.entity.DoctorDetail;
import com.hospital.norvic.entity.DoctorInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.helper.DoctorInformationHelper;
import com.hospital.norvic.pojo.request.DoctorPojoRequest;
import com.hospital.norvic.pojo.response.DoctorPojoResponse;
import com.hospital.norvic.repository.DoctorDetailRepository;
import com.hospital.norvic.util.GenericFileUtil;
import com.hospital.norvic.util.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorDetailServiceImpl implements DoctorDetailService {

    private final DoctorDetailRepository doctorDetailRepository;
    private final GenericFileUtil genericFileUtil;
    private final UserService userService;
    private final DoctorInfoService doctorInfoService;
    private final DoctorInformationHelper doctorInformationHelper;
    private final NullAwareBeanUtilsBean nullAwareBeanUtilsBean = new NullAwareBeanUtilsBean();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DoctorDetail saveDoctorDetail(DoctorPojoRequest doctorPojoRequest) throws Exception {
        UserInfo userInfo = UserInfo.builder()
                .firstName(doctorPojoRequest.getFirstName())
                .middleName(doctorPojoRequest.getMiddleName())
                .lastName(doctorPojoRequest.getLastName())
                .gender(doctorPojoRequest.getGender())
                .permanentAddress(doctorPojoRequest.getPermanentAddress())
                .temporaryAddress(doctorPojoRequest.getTemporaryAddress())
                .currentAddress(doctorPojoRequest.getCurrentAddress())
                .email(doctorPojoRequest.getEmail())
                .altEmail(doctorPojoRequest.getAltEmail())
                .phone(doctorPojoRequest.getPhone())
                .altPhone(doctorPojoRequest.getAltPhone())
                .mobile(doctorPojoRequest.getMobile())
                .altMobile(doctorPojoRequest.getAltMobile())
                .build();
        String imagePath = genericFileUtil.saveFile(doctorPojoRequest.getPhoto());
        DoctorInfo doctorInfo = DoctorInfo.builder()
                .photo(imagePath)
                .userInfo(userService.saveUser(userInfo))
                .build();
        DoctorDetail doctorDetail = DoctorDetail.builder()
                .doctorInfo(doctorInfoService.saveDoctorInfo(doctorInfo))
                .department(doctorPojoRequest.getDepartment())
                .designation(doctorPojoRequest.getDesignation())
                .roles(doctorPojoRequest.getRoles())
                .supervisorInfo(UserInfo.builder().id(doctorPojoRequest.getSupervisorInfo()).build())
                .newJoinee(doctorPojoRequest.isNewJoinee())
                .transferred(doctorPojoRequest.isTransferred())
                .build();

        return doctorDetailRepository.save(doctorDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.MANDATORY)
    public String updateDoctorDetail(DoctorPojoRequest doctorPojoRequest) throws Exception {
        DoctorDetail existingDoctorDetail = doctorDetailRepository.findById(doctorPojoRequest.getId())
                .orElseThrow(() -> new RuntimeException("Doctor Detail cannot found."));
        DoctorInfo existingDoctorInfo = existingDoctorDetail.getDoctorInfo();
        UserInfo existingUserInfo = existingDoctorInfo.getUserInfo();
        try {
            UserInfo userInfo = UserInfo.builder()
                    .id(existingUserInfo.getId())
                    .firstName(doctorPojoRequest.getFirstName())
                    .middleName(doctorPojoRequest.getMiddleName())
                    .lastName(doctorPojoRequest.getLastName())
                    .gender(doctorPojoRequest.getGender())
                    .permanentAddress(doctorPojoRequest.getPermanentAddress())
                    .temporaryAddress(doctorPojoRequest.getTemporaryAddress())
                    .currentAddress(doctorPojoRequest.getCurrentAddress())
                    .email(doctorPojoRequest.getEmail())
                    .altEmail(doctorPojoRequest.getAltEmail())
                    .phone(doctorPojoRequest.getPhone())
                    .altPhone(doctorPojoRequest.getAltPhone())
                    .mobile(doctorPojoRequest.getMobile())
                    .altMobile(doctorPojoRequest.getAltMobile())
                    .build();
            String image = null;
            if (!doctorPojoRequest.getPhoto().isEmpty()) {
                image = genericFileUtil.updateFile(doctorPojoRequest.getPhoto(), existingDoctorInfo.getPhoto());
            }
            DoctorInfo doctorInfo = DoctorInfo.builder()
                    .id(existingDoctorInfo.getId())
                    .userInfo(userService.updateUser(userInfo))
                    .photo(image)
                    .build();
            DoctorDetail doctorDetail = DoctorDetail.builder()
                    .id(doctorPojoRequest.getId())
                    .doctorInfo(doctorInfoService.updateDoctorInfo(doctorInfo))
                    .department(doctorPojoRequest.getDepartment())
                    .designation(doctorPojoRequest.getDesignation())
                    .roles(doctorPojoRequest.getRoles())
                    .supervisorInfo(UserInfo.builder().id(doctorPojoRequest.getSupervisorInfo()).build())
                    .newJoinee(doctorPojoRequest.isNewJoinee())
                    .transferred(doctorPojoRequest.isTransferred())
                    .lastBranch(doctorPojoRequest.getLastBranch())
                    .build();
            doctorDetailRepository.save(doctorDetail);
        } catch (Exception e) {
            genericFileUtil.reSaveFile(existingDoctorInfo.getPhoto());
        }
        return "Doctor Updated successfully.";
    }

    @Override
    public DoctorPojoResponse getDoctorDetailById(Integer id) throws MalformedURLException {
        return doctorInformationHelper.getDoctorInformationById(id);
    }

    @Override
    public List<DoctorPojoResponse> getAllDoctorDetail() {
        return doctorInformationHelper.getAllDoctorInformation();
    }
}
