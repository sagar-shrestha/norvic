package com.hospital.norvic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.NurseDetail;
import com.hospital.norvic.entity.NurseInfo;
import com.hospital.norvic.entity.UserInfo;
import com.hospital.norvic.helper.NurseInformationHelper;
import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.NursePojoRequest;
import com.hospital.norvic.pojo.response.NursePojoResponse;
import com.hospital.norvic.repository.NurseDetailRepository;
import com.hospital.norvic.util.GenericFileUtil;
import com.hospital.norvic.util.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NurseDetailServiceImpl implements NurseDetailService {

    private final NurseDetailRepository nurseDetailRepository;
    private final ObjectMapper objectMapper;
    private final BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
    private final GenericFileUtil genericFileUtil;
    private final UserService userService;
    private final NurseInfoService nurseInfoService;
    private final NurseInformationHelper nurseInformationHelper;

    @Override
    public NurseDetail saveNurseDetail(NursePojoRequest nursePojoRequest, ImageRequest imageRequest) throws Exception {
        UserInfo userInfo = objectMapper.convertValue(nursePojoRequest, UserInfo.class);
        userService.saveUser(userInfo);
        String image = genericFileUtil.saveFile(imageRequest.getPhoto());
        NurseInfo nurseInfo = objectMapper.convertValue(nursePojoRequest, NurseInfo.class);
        nurseInfo.setPhoto(image);
        nurseInfoService.saveNurseInfo(nurseInfo);
        NurseDetail nurseDetail = objectMapper.convertValue(nursePojoRequest, NurseDetail.class);
        return nurseDetailRepository.save(nurseDetail);
    }

    @Override
    public NurseDetail updateNurseDetail(NursePojoRequest nursePojoRequest, ImageRequest imageRequest) throws Exception {
        NurseDetail existingNurseDetail = nurseDetailRepository.findById(nursePojoRequest.getId())
                .orElseThrow(() -> new RuntimeException("Nurse Detail cannot be found."));
        NurseInfo existingNurseInfo = existingNurseDetail.getNurseInfo();
        UserInfo existingUserInfo = existingNurseInfo.getUserInfo();
        try {
            beanUtilsBean.copyProperties(existingUserInfo, nursePojoRequest);
            userService.updateUser(existingUserInfo);
            beanUtilsBean.copyProperties(existingNurseInfo, nursePojoRequest);
            String image = genericFileUtil.updateFile(imageRequest.getPhoto(), existingNurseInfo.getPhoto());
            existingNurseInfo.setPhoto(image);
            nurseInfoService.updateNurseInfo(existingNurseInfo);
            beanUtilsBean.copyProperties(existingNurseDetail, nursePojoRequest);
        } catch (Exception e) {
            genericFileUtil.reSaveFile(existingNurseInfo.getPhoto());
        }
        return nurseDetailRepository.save(existingNurseDetail);
    }

    @Override
    public NursePojoResponse getNurseById(Integer id) throws Exception {
        return nurseInformationHelper.getNurseInformationById(id);
    }

    @Override
    public List<NursePojoResponse> getAllNurse() throws Exception{
        return nurseInformationHelper.getAllNurseInformation();
    }
}
