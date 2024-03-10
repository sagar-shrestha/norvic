package com.hospital.norvic.service;

import com.hospital.norvic.entity.NurseInfo;
import com.hospital.norvic.pojo.request.NursePojoRequest;

public interface NurseInfoService {

    NurseInfo saveNurseInfo(NurseInfo nurseInfo);

    NurseInfo updateNurseInfo(NurseInfo nurseInfo);
}
