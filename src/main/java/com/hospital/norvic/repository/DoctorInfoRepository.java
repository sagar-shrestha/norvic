package com.hospital.norvic.repository;

import com.hospital.norvic.entity.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorInfoRepository extends JpaRepository<DoctorInfo, Integer> {
}
