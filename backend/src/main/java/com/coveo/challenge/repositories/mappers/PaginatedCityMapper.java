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
public class PaginatedCityMapper {
    private final CityMapper cityMapper;

    public PaginatedData<City> fromEntity(Page<CityEntity> paginatedEntity) {
        PaginatedData<City> paginatedData = new PaginatedData<>();

        paginatedData.setPage(paginatedEntity.getNumber());
        paginatedData.setTotalNumberOfPages(paginatedEntity.getTotalPages());
        paginatedData.setData(paginatedEntity.get().map(cityMapper::fromEntity).collect(Collectors.toList()));

        return paginatedData;
    }
}
