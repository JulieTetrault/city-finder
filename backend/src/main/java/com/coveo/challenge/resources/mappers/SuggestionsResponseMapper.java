package com.coveo.challenge.resources.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;

import lombok.RequiredArgsConstructor;

@Component
@Deprecated
@RequiredArgsConstructor
public class SuggestionsResponseMapper {
    private final CityResponseMapper cityResponseMapper;

    public SuggestionsResponse toResponse(List<City> cities) {
        List<CityResponse> cityResponses = cities.stream().map(cityResponseMapper::toResponse).toList();

        return SuggestionsResponse.builder().cities(cityResponses).build();
    }
}
