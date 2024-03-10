package com.hospital.norvic.controller;

import com.hospital.norvic.pojo.freeApiPojo.EntryDetails;
import com.hospital.norvic.pojo.freeApiPojo.User;
import lombok.Getter;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@RestController
@RequestMapping("/freeapi")
public class FreeApi implements Serializable {

    @GetMapping("/gett")
    public ResponseEntity<EntryDetails> getDataFromFreeApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EntryDetails> entryDetails = restTemplate
                .getForEntity("https://api.publicapis.org/entries", EntryDetails.class);
        return entryDetails;
    }



    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Validated User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity("http://localhost:9091/freeapi/saveuser", requestEntity, User.class);
        return  responseEntity;
    }

    @PutMapping
    public  ResponseEntity<User> updateUser(@RequestBody @Validated User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User>  requestEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<User> responseEntity = restTemplate
                .exchange("localhost:9091/freeapi/saveuser", HttpMethod.PUT, requestEntity, User.class);
        return responseEntity;
    }
}
