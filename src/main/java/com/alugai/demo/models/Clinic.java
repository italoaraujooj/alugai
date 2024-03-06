package com.alugai.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MyUser owner;

    private String name;

    private String Address;

    private Integer size; // (m²)

    private String description;

    private String type;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<BusinessHours> availableHours;

    @ElementCollection
    private List<BusinessHours> unavailableHours;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents;

}
