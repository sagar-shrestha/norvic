package com.hospital.norvic.service;

import com.hospital.norvic.entity.StaffInfo;

public interface StaffInfoService {

    StaffInfo saveStaffInfo(StaffInfo staffInfo);

    StaffInfo updateStaffInfo(StaffInfo staffInfo);

    StaffInfo getStaffById(Integer id);
}
