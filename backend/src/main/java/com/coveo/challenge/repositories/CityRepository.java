package com.coveo.challenge.repositories;

import java.util.List;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;

public interface CityRepository {
    City getById(long id);

    List<City> findAllBy(String name, Double latitude, Double longitude);

    PaginatedData<City> findPageBy(String name, Double latitude, Double longitude, Integer page);

    void saveAll(List<City> cities);
}
