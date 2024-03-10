package com.hospital.norvic.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.NurseDetail;
import com.hospital.norvic.entity.NurseInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.pojo.response.NursePojoResponse;
import com.hospital.norvic.repository.NurseDetailRepository;
import com.hospital.norvic.repository.NurseInfoRepository;
import com.hospital.norvic.repository.UserRepository;
import com.hospital.norvic.util.GenericFileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NurseInformationHelper {

    private final GenericFileUtil genericFileUtil;
    private final ObjectMapper objectMapper;
    private final NurseDetailRepository nurseDetailRepository;
    private final NurseInfoRepository nurseInfoRepository;
    private final UserRepository userRepository;

    public NursePojoResponse getNurseInformationById(Integer id) throws Exception {
        NurseDetail nurseDetail = nurseDetailRepository.getNurseInformationById(id);
        Resource image = genericFileUtil.getFile(nurseDetail.getNurseInfo().getPhoto());
        NursePojoResponse nursePojoResponse = objectMapper.convertValue(nurseDetail, NursePojoResponse.class);
        nursePojoResponse.setPhoto(image);
        return nursePojoResponse;
    }

    public List<NursePojoResponse> getAllNurseInformation() throws Exception {
        List<NurseDetail> nurseDetailList = nurseDetailRepository.findAll();
        List<NursePojoResponse> nursePojoResponseList = new ArrayList<>();

        nurseDetailList.forEach(nurseDetail -> {
            NurseInfo nurseInfo = nurseInfoRepository.findById(nurseDetail.getId())
                    .orElseThrow(() -> new RuntimeException("Nurse Info cannot be found."));
            UserInfo userInfo = userRepository.findById(nurseInfo.getUserInfo().getId())
                    .orElseThrow(() -> new RuntimeException("User Info cannot be found."));

            Resource image = null;
            try {
                image = genericFileUtil.getFile(nurseInfo.getPhoto());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            objectMapper.convertValue(nurseInfo, NursePojoResponse.class);
            objectMapper.convertValue(userInfo, NursePojoResponse.class);
            NursePojoResponse nursePojoResponse = objectMapper.convertValue(nurseDetail, NursePojoResponse.class);
            nursePojoResponse.setPhoto(image);
            nursePojoResponseList.add(nursePojoResponse);
        });
        return nursePojoResponseList;
    }
}
