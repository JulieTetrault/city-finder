package com.coveo.challenge.resources.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SuggestionsMapper {
    private final CityMapper cityMapper;

    public SuggestionsResponse toResponse(List<City> cities) {
        List<CityResponse> cityResponses = cities.stream().map(cityMapper::toResponse).toList();

        return SuggestionsResponse.builder().cities(cityResponses).build();
    }
}
