package com.hospital.norvic.service;

import com.hospital.norvic.entity.OrganizationInfo;

import java.util.List;

public interface OrganizationService {

    public String saveOrganization(OrganizationInfo organizationInfo);

    public String updateOrganization(OrganizationInfo organizationInfo);

    public List<OrganizationInfo> getOrganization();
}
