package com.alugai.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Clinic clinic;

    @ManyToOne
    private MyUser renter;

    private BusinessHours schedule;

    @Column(name= "rent-value")
    private Float rentValue;

    private String note;
}
