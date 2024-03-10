package com.hospital.norvic.controller;

import com.hospital.norvic.pojo.request.ImageRequest;
import com.hospital.norvic.pojo.request.NursePojoRequest;
import com.hospital.norvic.pojo.response.GlobalApiResponse;
import com.hospital.norvic.service.NurseDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final NurseDetailService nurseDetailService;

    @PostMapping
    public ResponseEntity<GlobalApiResponse> saveNurse(@ModelAttribute @Valid NursePojoRequest nursePojoRequest,
                                                       @ModelAttribute @Valid ImageRequest imageRequest) throws Exception {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(nurseDetailService.saveNurseDetail(nursePojoRequest, imageRequest))
                .message("Nurse saved successfully.")
                .status(true)
                .build());
    }

    @PutMapping
    public ResponseEntity<GlobalApiResponse> updateNurse(@ModelAttribute @Valid NursePojoRequest nursePojoRequest,
                                                         @ModelAttribute @Valid ImageRequest imageRequest) throws Exception {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(nurseDetailService.updateNurseDetail(nursePojoRequest, imageRequest))
                .message("Nurse updated successfully.")
                .status(true)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getNurseInformationById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(nurseDetailService.getNurseById(id))
                .message("Nurse Information found successfully.")
                .status(true)
                .build());
    }

    @GetMapping("allnurse")
    public ResponseEntity<GlobalApiResponse> getAllNurseInformation() throws Exception {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(nurseDetailService.getAllNurse())
                .message("All Nurse Information found Successfully.")
                .status(true)
                .build());
    }

}
