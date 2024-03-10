package com.hospital.norvic.service;

import com.hospital.norvic.entity.StaffInfo;
import com.hospital.norvic.repository.StaffInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffInfoServiceImpl implements StaffInfoService {

    private final StaffInfoRepository staffInfoRepository;

    @Override
    public StaffInfo saveStaffInfo(StaffInfo staffInfo) {
        return staffInfoRepository.save(staffInfo);
    }

    @Override
    public StaffInfo updateStaffInfo(StaffInfo staffInfo) {
        return staffInfoRepository.save(staffInfo);
    }

    @Override
    public StaffInfo getStaffById(Integer id) {
        return staffInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff Info cannot be found."));
    }
}
