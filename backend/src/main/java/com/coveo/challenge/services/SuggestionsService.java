package com.coveo.challenge.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.CityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestionsService {

    private final CityRepository cityRepository;

    public List<City> getCities(String name, Double latitude, Double longitude)
    {
        return cityRepository.findAllBy(name, latitude, longitude);
    }

    public PaginatedData<City> getPaginatedCities(String name, Double latitude, Double longitude, Integer page)
    {
        return cityRepository.findPageBy(name, latitude, longitude, page);
    }
}
