package com.coveo.challenge.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.mappers.CityMapper;
import com.coveo.challenge.repositories.mappers.PaginatedCityMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {
    protected static final int PAGE_SIZE = 5;

    private final CityMapper cityMapper;
    private final PaginatedCityMapper paginatedCityMapper;
    private final CityEntityRepository cityEntityRepository;

    @Override
    public List<City> findAllBy(String name, double latitude, double longitude) {
        return cityEntityRepository.findByFilters(name, latitude, longitude).stream().map(cityMapper::fromEntity).toList();
    }

    @Override
    public PaginatedData<City> findPageBy(String name, double latitude, double longitude, Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return paginatedCityMapper.fromEntity(cityEntityRepository.findPageByFilters(name, latitude, longitude, pageable));
    }

    @Override
    public void saveAll(List<City> cities) {
        cityEntityRepository.saveAll(cities.stream().map(cityMapper::toEntity).toList());
    }
}
