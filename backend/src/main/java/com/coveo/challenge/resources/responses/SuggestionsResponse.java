package com.coveo.challenge.resources.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Deprecated
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionsResponse {
    private List<CityResponse> cities;
}
