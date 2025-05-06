package com.tecnologiaefinancas.authify.service;

import com.tecnologiaefinancas.authify.io.ProfileRequest;
import com.tecnologiaefinancas.authify.io.ProfileResponse;

public interface ProfileService {

    ProfileResponse createProfile(ProfileRequest request);

    ProfileResponse getProfile(String email);
}
