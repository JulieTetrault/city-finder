package com.coveo.challenge.resources.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty("alt_name")
    private String altName;
    private Float latitude;
    private Float longitude;
    private String country;
    private Long population;
    private Long elevation;
    private String tz;
    @JsonProperty("modified_at")
    private String modifiedAt;
    @JsonProperty("feat_class")
    private String featClass;
    @JsonProperty("feat_code")
    private String featCode;
    private String cc2;
    private String dem;
    private String admin1;
    private String admin2;
    private String admin3;
    private String admin4;
}
