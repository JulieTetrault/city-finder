package com.coveo.challenge.testUtils.fixtures;

import java.util.List;

import com.coveo.challenge.repositories.entities.CityEntity;

public class CityEntityFixture {
    private static final long IDENTIFIER = 12345;
    private static final String NAME = "name";
    private static final String ASCII = "ascii";
    private static final List<String> ALT_NAMES = List.of("firstAltName", "secondAltName");
    private static final float LATITUDE = 20;
    private static final float LONGITUDE = 30;
    private static final String COUNTRY = "country";
    private static final long POPULATION = 12345;
    private static final long ELEVATION = 3000;
    private static final String TIME_ZONE = "America/Toronto";
    private static final String MODIFIED_AT = "2010-01-29";
    private static final String FEAT_CLASS = "featClass";
    private static final String FEAT_CODE = "featCode";
    private static final String CC2 = "cc2";
    private static final String DEM = "dem";
    private static final String ADMIN1 = "admin1";
    private static final String ADMIN2 = "admin2";
    private static final String ADMIN3 = "admin3";
    private static final String ADMIN4 = "admin4";

    private final CityEntity cityEntity;

    public CityEntityFixture() {
        cityEntity = CityEntity.builder()
                .identifier(IDENTIFIER)
                .name(NAME)
                .ascii(ASCII)
                .altNames(ALT_NAMES)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .country(COUNTRY)
                .population(POPULATION)
                .elevation(ELEVATION)
                .timeZone(TIME_ZONE)
                .modifiedAt(MODIFIED_AT)
                .featClass(FEAT_CLASS)
                .featCode(FEAT_CODE)
                .cc2(CC2)
                .dem(DEM)
                .admin1(ADMIN1)
                .admin2(ADMIN2)
                .admin3(ADMIN3)
                .admin4(ADMIN4)
                .build();
    }

    public CityEntityFixture withIdentifier(long identifier) {
        cityEntity.setIdentifier(identifier);
        return this;
    }

    public CityEntityFixture withName(String name) {
        cityEntity.setName(name);
        return this;
    }

    public CityEntityFixture withLatitude(double latitude) {
        cityEntity.setLatitude(latitude);
        return this;
    }

    public CityEntityFixture withLongitude(double longitude) {
        cityEntity.setLongitude(longitude);
        return this;
    }

    public CityEntity build() {
        return cityEntity;
    }
}
