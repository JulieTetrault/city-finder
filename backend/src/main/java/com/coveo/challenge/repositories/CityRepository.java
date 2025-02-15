package com.coveo.challenge.repositories;

import java.util.List;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;

public interface CityRepository {
    List<City> findAllBy(String name, double latitude, double longitude);
    PaginatedData<City> findPageBy(String name, double latitude, double longitude, Integer page);
    void saveAll(List<City> cities);
}
