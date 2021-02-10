package com.test.controller;

import com.test.dto.request.UserRequestDto;
import com.test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(path = "locality/{countyCode}")
    public ResponseEntity<?> getLocalities(@PathVariable String countyCode) {
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getLocalities(countyCode));
    }

    @GetMapping(path = "user/locality/{localityId}")
    public ResponseEntity<?> getUsersByLocality(@PathVariable Long localityId) {
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getUsersByLocality(localityId));
    }

    @GetMapping(path = "user/county/{countyId}")
    public ResponseEntity<?> getUsersByCounty(@PathVariable Long countyId) {
        return ResponseEntity.status(HttpStatus.OK).body(applicationService.getUsersByCounty(countyId));
    }

    @PostMapping(path = "user")
    public ResponseEntity<?> addUser(@RequestBody UserRequestDto requestDto) {
        applicationService.addUser(requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto requestDto, @PathVariable Long id) {
        applicationService.updateUser(requestDto, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        applicationService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
