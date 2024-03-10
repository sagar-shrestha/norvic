package com.hospital.norvic.controller;


import com.hospital.norvic.entity.OrganizationInfo;
import com.hospital.norvic.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    private String saveOrganization(@RequestBody OrganizationInfo organizationInfo) {
        return organizationService.saveOrganization(organizationInfo);
    }

    @PutMapping
    private String updateOrganization(OrganizationInfo organizationInfo) {
        return organizationService.updateOrganization(organizationInfo);
    }

    @GetMapping("/getOrganization")
    private List<OrganizationInfo> getOrganization() {
        return organizationService.getOrganization();
    }
}
