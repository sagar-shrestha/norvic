package com.hospital.norvic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.StaffDetail;
import com.hospital.norvic.entity.StaffInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.helper.StaffInformationHelper;
import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.StaffPojoRequest;
import com.hospital.norvic.pojo.response.StaffPojoResponse;
import com.hospital.norvic.repository.StaffDetailRepository;
import com.hospital.norvic.util.GenericFileUtil;
import com.hospital.norvic.util.NullAwareBeanUtilsBean;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffDetailServiceImpl implements StaffDetailService {

    private final StaffDetailRepository staffDetailRepository;
    private final ObjectMapper objectMapper;
    private final StaffInfoService staffInfoService;
    private final UserService userService;
    private final GenericFileUtil genericFileUtil;
    private final StaffInformationHelper staffInformationHelper;
    private final BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();


    @Override
    public String saveStaffDetail(StaffPojoRequest staffPojoRequest, ImageRequest imageRequest) throws IOException {
        UserInfo userInfo = objectMapper.convertValue(staffPojoRequest, UserInfo.class);
        userService.saveUser(userInfo);
        StaffInfo staffInfo = objectMapper.convertValue(staffPojoRequest, StaffInfo.class);
        String image = genericFileUtil.saveFile(imageRequest.getPhoto());
        staffInfo.setPhoto(image);
        staffInfo.setUserInfo(userInfo);
        staffInfoService.saveStaffInfo(staffInfo);
        staffDetailRepository.save(objectMapper.convertValue(staffPojoRequest, StaffDetail.class));
        return "Staff saved successfully.";
    }

    @Override
    public String updateStaffDetail(StaffPojoRequest staffPojoRequest, ImageRequest imageRequest) throws IOException {
        StaffDetail existingStaffDetail = staffDetailRepository.findById(staffPojoRequest.getId())
                .orElseThrow(() -> new RuntimeException("Staff Detail cannot be found."));
        StaffInfo existingStaffInfo = existingStaffDetail.getStaffInfo();
        UserInfo existingUserInfo = existingStaffInfo.getUserInfo();
        try {
            beanUtilsBean.copyProperties(existingUserInfo, staffPojoRequest);
            userService.updateUser(existingUserInfo);
            beanUtilsBean.copyProperties(existingStaffInfo, staffPojoRequest);
            String image = genericFileUtil.updateFile(imageRequest.getPhoto(), existingStaffInfo.getPhoto());
            existingStaffInfo.setPhoto(image);
            existingStaffInfo.setUserInfo(existingUserInfo);
            staffInfoService.updateStaffInfo(existingStaffInfo);
            beanUtilsBean.copyProperties(existingStaffDetail, staffPojoRequest);
            staffDetailRepository.save(existingStaffDetail);
        } catch (Exception e) {
            genericFileUtil.reSaveFile(existingStaffInfo.getPhoto());
        }
        return "Staff Updated successfully.";
    }

    @Override
    public StaffPojoResponse getStaffById(Integer id) throws MalformedURLException {
        return staffInformationHelper.getStaffInformationById(id);
    }

    @Override
    public List<StaffPojoResponse> getAllStaff(){
        return staffInformationHelper.getAllStaffInformation();
    }
}
