package com.hospital.norvic.repository;


import com.hospital.norvic.entity.OrganizationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<OrganizationInfo, Integer> {
}
