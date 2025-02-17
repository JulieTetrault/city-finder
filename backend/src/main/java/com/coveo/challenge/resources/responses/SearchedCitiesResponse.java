package com.coveo.challenge.resources.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchedCitiesResponse {
    private List<SearchedCityResponse> cities;
}
