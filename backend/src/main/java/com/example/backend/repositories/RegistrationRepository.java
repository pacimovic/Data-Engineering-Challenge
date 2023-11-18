package com.example.backend.repositories;

import com.example.backend.entities.Registration;

import java.util.List;

public interface RegistrationRepository {
    public List<Registration> allRegistratiion();
    public Registration userRegistration(String user_id);
}
