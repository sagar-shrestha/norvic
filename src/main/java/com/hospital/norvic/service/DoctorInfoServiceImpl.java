package com.hospital.norvic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.norvic.entity.DoctorInfo;
import com.hospital.norvic.repository.DoctorInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorInfoServiceImpl implements DoctorInfoService {

    private final ObjectMapper objectMapper;
    private final DoctorInfoRepository doctorInfoRepository;

    @Override
    public DoctorInfo saveDoctorInfo(DoctorInfo doctorInfo) {
        return doctorInfoRepository.save(doctorInfo);
    }

    @Override
    public DoctorInfo updateDoctorInfo(DoctorInfo doctorInfo) {
        return doctorInfoRepository.save(doctorInfo);
    }

//    @Override
//    public DoctorPojoRequest getDoctorInfoById(Integer id) {
//        DoctorInfo doctorInfo = doctorInfoRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Doctor Info is unavailable."));
//        DoctorPojoRequest doctorInfoPojo = objectMapper.convertValue(doctorInfo, DoctorPojoRequest.class);
//        return doctorInfoPojo;
//    }

    @Override
    public DoctorInfo getDoctorInfoById(Integer id) {
        return doctorInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor Info cannot found."));
    }

    @Override
    public List<DoctorInfo> getAllDoctorInfo() {
        return doctorInfoRepository.findAll();
    }

    @Override
    public String deleteDoctorInfoById(Integer id) {
        doctorInfoRepository.deleteById(id);
        return "Doctor Info deleted successfully!!!";
    }
}
