package com.alugai.demo.repositories;

import com.alugai.demo.models.Clinic;
import com.alugai.demo.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Optional<Clinic> findByName(String clinicName);

    Optional<Clinic> findByOwner(MyUser owner);
}
