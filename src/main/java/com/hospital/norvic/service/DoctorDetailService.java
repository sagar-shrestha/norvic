package com.hospital.norvic.service;

import com.hospital.norvic.pojo.request.DoctorPojoRequest;
import com.hospital.norvic.pojo.response.DoctorPojoResponse;

import java.net.MalformedURLException;
import java.util.List;

public interface DoctorDetailService {

    void saveDoctorDetail(DoctorPojoRequest doctorPojoRequest) throws Exception;


    String updateDoctorDetail(DoctorPojoRequest doctorPojoRequest) throws Exception;

    //  DoctorPojoRequest getDoctorDetailById(Integer id) throws InvocationTargetException, IllegalAccessException;
    DoctorPojoResponse getDoctorDetailById(Integer id) throws MalformedURLException;

    List<DoctorPojoResponse> getAllDoctorDetail();
}
