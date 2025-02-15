package com.coveo.challenge.resources.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.PaginatedSuggestionsResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaginatedSuggestionsMapper {
    private final CityMapper cityMapper;

    public PaginatedSuggestionsResponse toResponse(PaginatedData<City> paginatedCities) {
        List<CityResponse> cityResponses = paginatedCities.getData().stream().map(cityMapper::toResponse).toList();

        return PaginatedSuggestionsResponse.builder()
                .page(paginatedCities.getPage())
                .totalNumberOfPages(paginatedCities.getTotalNumberOfPages())
                .cities(cityResponses)
                .build();
    }
}
