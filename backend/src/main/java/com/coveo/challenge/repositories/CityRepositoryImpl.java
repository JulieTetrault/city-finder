package com.coveo.challenge.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.mappers.CityEntityMapper;
import com.coveo.challenge.repositories.mappers.PaginatedCityEntityMapper;
import com.coveo.challenge.repositories.specifications.CitySpecificationBuilder;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CityRepositoryImpl implements CityRepository {
    protected static final int PAGE_SIZE = 5;

    private final CityEntityMapper cityEntityMapper;
    private final PaginatedCityEntityMapper paginatedCityMapper;
    private final CityEntityRepository cityEntityRepository;
    private final CitySpecificationBuilder citySpecificationBuilder;

    @Override
    public City getById(long id) {
        Optional<City> city = cityEntityRepository.findByIdentifier(id);

        if (city.isEmpty()) {
            throw new NotFoundException(String.format("City with id '%s' not found", id));
        }

        return city.get();
    }

    @Override
    public List<City> findAllBy(String name, Double latitude, Double longitude) {
        return cityEntityRepository.findAll(citySpecificationBuilder.build(name, latitude, longitude)).stream().map(cityEntityMapper::fromEntity).toList();
    }

    @Override
    public PaginatedData<City> findPageBy(String name, Double latitude, Double longitude, Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return paginatedCityMapper.fromEntity(cityEntityRepository.findAll(citySpecificationBuilder.build(name, latitude, longitude), pageable));
    }

    @Override
    public void saveAll(List<City> cities) {
        cityEntityRepository.saveAll(cities.stream().map(cityEntityMapper::toEntity).toList());
    }
}
