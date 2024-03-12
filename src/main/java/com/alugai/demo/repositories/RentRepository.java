package com.alugai.demo.repositories;

import com.alugai.demo.models.Clinic;
import com.alugai.demo.models.MyUser;
import com.alugai.demo.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {

    Optional<Rent> findByClinic(Clinic clinic);

    Optional<Rent> findByRenter(MyUser renter);


}
