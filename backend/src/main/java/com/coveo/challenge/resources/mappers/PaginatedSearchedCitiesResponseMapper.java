package com.coveo.challenge.resources.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.responses.PaginatedSearchedCitiesResponse;
import com.coveo.challenge.resources.responses.SearchedCityResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaginatedSearchedCitiesResponseMapper {
    private final SearchedCityResponseMapper searchedCityResponseMapper;

    public PaginatedSearchedCitiesResponse toResponse(PaginatedData<City> paginatedCities) {
        List<SearchedCityResponse> searchedCitiesResponse = paginatedCities.getData().stream().map(searchedCityResponseMapper::toResponse).toList();

        return PaginatedSearchedCitiesResponse.builder()
                .page(paginatedCities.getPage())
                .totalNumberOfPages(paginatedCities.getTotalNumberOfPages())
                .cities(searchedCitiesResponse)
                .build();
    }
}
