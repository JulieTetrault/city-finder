package com.coveo.challenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.coveo.challenge.models.City;
import com.coveo.challenge.repositories.entities.CityEntity;

public interface CityEntityRepository extends JpaRepository<CityEntity, Long>, JpaSpecificationExecutor<CityEntity> {
    Optional<City> findByIdentifier(long identifier);
}
