package com.hospital.norvic.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.StaffDetail;
import com.hospital.norvic.entity.StaffInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.pojo.response.StaffPojoResponse;
import com.hospital.norvic.repository.StaffDetailRepository;
import com.hospital.norvic.util.GenericFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StaffInformationHelper {

    private final StaffDetailRepository staffDetailRepository;
    private final GenericFileUtil genericFileUtil;
    private final ObjectMapper objectMapper;


    public StaffPojoResponse getStaffInformationById(Integer id) throws MalformedURLException {
        StaffDetail existingStaffDetail = staffDetailRepository.getNurseInformationById(id);
        Resource image = genericFileUtil.getFile(existingStaffDetail.getStaffInfo().getPhoto());
        StaffPojoResponse staffPojoResponse = objectMapper.convertValue(existingStaffDetail, StaffPojoResponse.class);
        staffPojoResponse.setPhoto(image);
        return staffPojoResponse;
    }

    public List<StaffPojoResponse> getAllStaffInformation() {
        List<StaffDetail> existingStaffDetailList = staffDetailRepository.findAll();
        List<StaffPojoResponse> staffPojoResponseList = new ArrayList<>();

        existingStaffDetailList.forEach(staffDetail -> {
            StaffInfo staffInfo = staffDetail.getStaffInfo();
            UserInfo userInfo = staffInfo.getUserInfo();
            Resource image = null;
            try {
                image = genericFileUtil.getFile(staffInfo.getPhoto());
            } catch (Exception e) {
                throw new RuntimeException();
            }
            objectMapper.convertValue(staffInfo, StaffPojoResponse.class);
            objectMapper.convertValue(userInfo, StaffPojoResponse.class);
            StaffPojoResponse staffPojoResponse = objectMapper.convertValue(staffDetail, StaffPojoResponse.class);
            staffPojoResponse.setPhoto(image);
            staffPojoResponseList.add(staffPojoResponse);
        });
        return staffPojoResponseList;
    }

}
