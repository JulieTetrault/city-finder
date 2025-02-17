package com.coveo.challenge.resources.mappers;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.SearchedCityResponse;

@Component
public class SearchedCityResponseMapper {
    public SearchedCityResponse toResponse(City city) {
        SearchedCityResponse searchedCityResponse = new SearchedCityResponse();

        searchedCityResponse.setId(city.getIdentifier());
        searchedCityResponse.setAscii(city.getAscii());

        return searchedCityResponse;
    }
}
