package com.coveo.challenge.repositories.specifications;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.coveo.challenge.configurations.GeolocationProperties;
import com.coveo.challenge.repositories.entities.CityEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CitySpecificationBuilder {
    private final GeolocationProperties geolocationProperties;

    public Specification<CityEntity> build(String name, Double latitude, Double longitude) {
        return Specification.where(CitySpecification.nameLike(name))
                .and(CitySpecification.latitudeNear(
                        Optional.ofNullable(latitude).orElse(geolocationProperties.getDefaultLatitude()),
                        geolocationProperties.getLatitudeRange()
                ))
                .and(CitySpecification.longitudeNear(
                        Optional.ofNullable(longitude).orElse(geolocationProperties.getDefaultLongitude()),
                        geolocationProperties.getLongitudeRange()
                ));
    }
}
