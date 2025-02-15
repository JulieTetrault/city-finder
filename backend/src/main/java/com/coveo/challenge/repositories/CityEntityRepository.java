package com.coveo.challenge.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.repositories.queries.CityQueries;

public interface CityEntityRepository extends JpaRepository<CityEntity, Long> {
    @Query(CityQueries.FILTER_CITIES_QUERY)
    List<CityEntity> findByFilters(@Param("name") String name, @Param("latitude") Double latitude, @Param("longitude") Double longitude);

    @Query(CityQueries.FILTER_CITIES_QUERY)
    Page<CityEntity> findPageByFilters(@Param("name") String name, @Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);
}
