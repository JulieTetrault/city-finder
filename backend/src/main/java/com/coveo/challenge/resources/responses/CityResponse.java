package com.coveo.challenge.resources.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityResponse {
    private long id;
    private String name;
    private String ascii;
    private String alt_name;
    private Float latitude;
    private Float longitude;
    private String country;
    private Long population;
    private Long elevation;
    private String tz;
    private String modified_at;
    private String feat_class;
    private String feat_code;
    private String cc2;
    private String dem;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
}
