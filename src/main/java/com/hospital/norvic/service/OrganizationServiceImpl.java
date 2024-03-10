package com.hospital.norvic.service;

import com.hospital.norvic.entity.OrganizationInfo;
import com.hospital.norvic.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public String saveOrganization(OrganizationInfo organizationInfo) {
        return null;
    }

    @Override
    public String updateOrganization(OrganizationInfo existingOrganizationInfo) {
        return null;
    }

    @Override
    public List<OrganizationInfo> getOrganization() {
        return null;
    }
}
