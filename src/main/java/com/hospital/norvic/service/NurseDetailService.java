package com.hospital.norvic.service;

import com.hospital.norvic.entity.NurseDetail;
import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.NursePojoRequest;
import com.hospital.norvic.pojo.response.NursePojoResponse;

import java.util.List;

public interface NurseDetailService {

    NurseDetail saveNurseDetail(NursePojoRequest nursePojoRequest, ImageRequest imageRequest) throws Exception;

    NurseDetail updateNurseDetail(NursePojoRequest nursePojoRequest, ImageRequest imageRequest) throws Exception;

    NursePojoResponse getNurseById(Integer id) throws Exception;

    List<NursePojoResponse> getAllNurse() throws Exception;
}
