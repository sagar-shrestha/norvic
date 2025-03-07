package com.hospital.norvic.controller;

import com.hospital.norvic.pojo.request.DoctorPojoRequest;
import com.hospital.norvic.pojo.response.DoctorPojoResponse;
import com.hospital.norvic.pojo.response.GlobalApiResponse;
import com.hospital.norvic.service.DoctorDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/doctorinfo")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorDetailService doctorDetailService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GlobalApiResponse> saveDoctorInfo(@ModelAttribute @Valid DoctorPojoRequest doctorPojoRequest)
            throws Exception {
                doctorDetailService.saveDoctorDetail(doctorPojoRequest);

        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(null)
                .message("la hai vayo hai save")
                .status(true)
                .build());
    }

    @PutMapping
    public ResponseEntity<GlobalApiResponse> updateDoctor(@ModelAttribute @Valid DoctorPojoRequest doctorPojoRequest)
            throws Exception {
        String string = doctorDetailService.updateDoctorDetail(doctorPojoRequest);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(string)
                .message("Doctor updated successfully!!!")
                .status(true).build());
    }

    @PostMapping
    public ResponseEntity<GlobalApiResponse> saveAndUpdate(@ModelAttribute @Valid DoctorPojoRequest doctorPojoRequest) throws Exception {
        String string = doctorDetailService.saveAndUpdateDoctor(doctorPojoRequest);

        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(null)
                .message(string)
                .status(true)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getDoctorById(@PathVariable Integer id) throws MalformedURLException {
        DoctorPojoResponse doctorPojoResponse = doctorDetailService.getDoctorDetailById(id);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(doctorPojoResponse)
                .message("Doctor found successfully.")
                .status(true).build());
    }

    @GetMapping("getalldoctor")
    public ResponseEntity<GlobalApiResponse> getAllDoctor() {
        List<DoctorPojoResponse> doctorPojoResponseList = doctorDetailService.getAllDoctorDetail();
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .data(doctorPojoResponseList)
                .message("Doctors found successfully.")
                .status(true)
                .build());
    }
}