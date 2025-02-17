package com.coveo.challenge.repositories.mappers;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.entities.CityEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaginatedCityEntityMapper {
    private final CityEntityMapper cityEntityMapper;

    public PaginatedData<City> fromEntity(Page<CityEntity> paginatedCityEntity) {
        PaginatedData<City> paginatedData = new PaginatedData<>();

        paginatedData.setPage(paginatedCityEntity.getNumber());
        paginatedData.setTotalNumberOfPages(paginatedCityEntity.getTotalPages());
        paginatedData.setData(paginatedCityEntity.get().map(cityEntityMapper::fromEntity).collect(Collectors.toList()));

        return paginatedData;
    }
}
