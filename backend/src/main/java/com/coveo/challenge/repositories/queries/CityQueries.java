package com.coveo.challenge.repositories.queries;

public class CityQueries {
    public static final String FILTER_CITIES_QUERY =
            "SELECT c FROM CityEntity c WHERE " +
                    "(LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
                    "AND (ABS(c.latitude - :latitude) <= 10) " +
                    "AND (ABS(c.longitude - :longitude) <= 20)";
}
