package com.coveo.challenge.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City
{
    private long identifier;
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
