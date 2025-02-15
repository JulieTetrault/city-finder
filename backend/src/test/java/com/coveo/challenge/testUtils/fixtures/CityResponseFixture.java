package com.coveo.challenge.testUtils.fixtures;

import com.coveo.challenge.resources.responses.CityResponse;

public class CityResponseFixture {
    private static final long ID = 12345;
    private static final String NAME = "name";
    private static final String ASCII = "ascii";
    private static final String ALT_NAME = "firstAltName,secondAltName";
    private static final float LATITUDE = 20f;
    private static final float LONGITUDE = 30f;
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

    private final CityResponse cityResponse;

    public CityResponseFixture() {
        cityResponse = CityResponse.builder()
                .id(ID)
                .name(NAME)
                .ascii(ASCII)
                .alt_name(ALT_NAME)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .country(COUNTRY)
                .population(POPULATION)
                .elevation(ELEVATION)
                .tz(TIME_ZONE)
                .modified_at(MODIFIED_AT)
                .feat_class(FEAT_CLASS)
                .feat_code(FEAT_CODE)
                .cc2(CC2)
                .dem(DEM)
                .admin1(ADMIN1)
                .admin2(ADMIN2)
                .admin3(ADMIN3)
                .admin4(ADMIN4)
                .build();
    }

    public CityResponseFixture withId(long id) {
        cityResponse.setId(id);
        return this;
    }

    public CityResponse build() {
        return cityResponse;
    }
}
