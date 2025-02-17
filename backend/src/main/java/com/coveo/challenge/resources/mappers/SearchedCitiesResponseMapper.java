package com.coveo.challenge.resources.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.SearchedCitiesResponse;
import com.coveo.challenge.resources.responses.SearchedCityResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchedCitiesResponseMapper {
    private final SearchedCityResponseMapper searchedCityResponseMapper;

    public SearchedCitiesResponse toResponse(List<City> cities) {
        List<SearchedCityResponse> searchedCitiesResponse = cities.stream().map(searchedCityResponseMapper::toResponse).toList();

        return SearchedCitiesResponse.builder().cities(searchedCitiesResponse).build();
    }
}
