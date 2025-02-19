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
public class PaginatedSuggestionsResponse {
    private Integer page;
    private Integer totalNumberOfPages;
    private List<CityResponse> cities;
}
