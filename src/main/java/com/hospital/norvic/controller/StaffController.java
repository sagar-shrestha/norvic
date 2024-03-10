package com.hospital.norvic.controller;

import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.StaffPojoRequest;
import com.hospital.norvic.pojo.response.GlobalApiResponse;
import com.hospital.norvic.service.StaffDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffDetailService staffDetailService;

    @PostMapping
    public ResponseEntity<GlobalApiResponse> saveStaff(@ModelAttribute @Valid StaffPojoRequest staffPojoRequest,
                                                       @ModelAttribute @Valid ImageRequest imageRequest) throws IOException {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(staffDetailService.saveStaffDetail(staffPojoRequest, imageRequest))
                .message("Staff saved successfully.")
                .status(true)
                .build());
    }

    @PutMapping
    public ResponseEntity<GlobalApiResponse> updateStaff(@ModelAttribute @Valid StaffPojoRequest staffPojoRequest,
                                                         @ModelAttribute @Valid ImageRequest imageRequest) throws IOException {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(staffDetailService.updateStaffDetail(staffPojoRequest, imageRequest))
                .message("Staff updated successfully.")
                .status(true)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getStaffById(@PathVariable Integer id) throws MalformedURLException {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(staffDetailService.getStaffById(id))
                .message("Staff found successfully.")
                .status(true)
                .build());
    }

    @GetMapping("/getallstaff")
    public ResponseEntity<GlobalApiResponse> getAllStaff() {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(staffDetailService.getAllStaff())
                .message("All Staff found successfully.")
                .status(true)
                .build());
    }


}
