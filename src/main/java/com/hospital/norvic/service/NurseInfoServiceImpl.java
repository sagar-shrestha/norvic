package com.hospital.norvic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.NurseInfo;
import com.hospital.norvic.pojo.request.NursePojoRequest;
import com.hospital.norvic.repository.NurseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NurseInfoServiceImpl implements NurseInfoService {

    private final NurseInfoRepository nurseInfoRepository;
    private final ObjectMapper objectMapper;

    @Override
    public NurseInfo saveNurseInfo(NurseInfo nurseInfo) {
        return nurseInfoRepository.save(nurseInfo);
    }

    @Override
    public NurseInfo updateNurseInfo(NurseInfo nurseInfo) {
        return nurseInfoRepository.save(nurseInfo);
    }
}
