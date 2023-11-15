package com.example.backend.services;

import com.example.backend.entities.Registration;
import com.example.backend.repositories.RegistrationRepository;

import javax.inject.Inject;
import java.util.List;

public class RegistrationService {

    @Inject
    private RegistrationRepository registrationRepository;

    public List<Registration> allRegistrations(){
        return this.registrationRepository.allRegistratiion();
    }
}
