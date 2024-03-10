package com.hospital.norvic.service;

import com.hospital.norvic.entity.StaffDetail;
import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.StaffPojoRequest;
import com.hospital.norvic.pojo.response.StaffPojoResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface StaffDetailService {

    String saveStaffDetail(StaffPojoRequest staffPojoRequest, ImageRequest imageRequest) throws IOException;

    String updateStaffDetail(StaffPojoRequest staffPojoRequest, ImageRequest imageRequest) throws IOException;

    StaffPojoResponse getStaffById(Integer id) throws MalformedURLException;

    List<StaffPojoResponse> getAllStaff();
}
