package com.alugai.demo.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BusinessHours {
    private LocalDateTime start;
    private LocalDateTime finish;
}
