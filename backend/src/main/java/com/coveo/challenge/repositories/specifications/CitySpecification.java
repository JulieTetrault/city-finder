package com.coveo.challenge.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.coveo.challenge.repositories.entities.CityEntity;

public class CitySpecification {
    public static Specification<CityEntity> nameLike(String name) {
        if (name == null || name.isEmpty()) {
            return (root, query, builder) -> builder.conjunction();
        }

        return (root, query, builder) -> builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<CityEntity> latitudeNear(double latitude, double latitudeRange) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(builder.abs(builder.diff(root.get("latitude"), latitude)), latitudeRange);
    }

    public static Specification<CityEntity> longitudeNear(double longitude, double longitudeRange) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(builder.abs(builder.diff(root.get("longitude"), longitude)), longitudeRange);
    }
}
