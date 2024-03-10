package com.hospital.norvic.service;

import com.hospital.norvic.entity.DoctorInfo;

import java.util.List;

public interface DoctorInfoService {

    DoctorInfo saveDoctorInfo(DoctorInfo doctorInfo);

    DoctorInfo updateDoctorInfo(DoctorInfo doctorInfo);

   // DoctorPojoRequest getDoctorInfoById(Integer id);
    DoctorInfo getDoctorInfoById(Integer id);

    List<DoctorInfo> getAllDoctorInfo();

    String deleteDoctorInfoById(Integer id);
}
