package com.coveo.challenge.repositories.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long identifier;
    private String name;
    private String ascii;
    private List<String> altNames;
    private double latitude;
    private double longitude;
    private String country;
    private Long population;
    private Long elevation;
    private String timeZone;
    private String modifiedAt;
    private String featClass;
    private String featCode;
    private String cc2;
    private String dem;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
}
