package com.tecnologiaefinancas.authify.controller;

import com.tecnologiaefinancas.authify.io.ProfileRequest;
import com.tecnologiaefinancas.authify.io.ProfileResponse;
import com.tecnologiaefinancas.authify.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request){
        ProfileResponse response = profileService.createProfile(request);
        //TODO: send welcome email
        return response;
    }
}
