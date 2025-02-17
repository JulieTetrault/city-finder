package com.coveo.challenge.repositories.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coveo.challenge.models.City;
import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.testUtils.fixtures.CityEntityFixture;
import com.coveo.challenge.testUtils.fixtures.CityFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("CityEntityMapper")
public class CityEntityMapperTest {
    private static final CityEntity SOME_CITY_ENTITY = new CityEntityFixture().build();
    private static final City SOME_CITY = new CityFixture().build();

    @InjectMocks
    private CityEntityMapper cityEntityMapper;

    @Nested
    @DisplayName("when mapping to entity")
    class WhenMappingToEntity {

        @Test
        @DisplayName("it should return mapped city entity")
        void itShouldReturnMappedCityEntity() {
            CityEntity cityEntity = cityEntityMapper.toEntity(SOME_CITY);

            assertThat(cityEntity.getIdentifier()).isEqualTo(SOME_CITY.getIdentifier());
            assertThat(cityEntity.getName()).isEqualTo(SOME_CITY.getName());
            assertThat(cityEntity.getAscii()).isEqualTo(SOME_CITY.getAscii());
            assertThat(cityEntity.getAltNames()).isEqualTo(SOME_CITY.getAltNames());
            assertThat(cityEntity.getLatitude()).isEqualTo(SOME_CITY.getLatitude());
            assertThat(cityEntity.getLongitude()).isEqualTo(SOME_CITY.getLongitude());
            assertThat(cityEntity.getFeatClass()).isEqualTo(SOME_CITY.getFeatClass());
            assertThat(cityEntity.getFeatCode()).isEqualTo(SOME_CITY.getFeatCode());
            assertThat(cityEntity.getCountry()).isEqualTo(SOME_CITY.getCountry());
            assertThat(cityEntity.getAdmin1()).isEqualTo(SOME_CITY.getAdmin1());
            assertThat(cityEntity.getAdmin2()).isEqualTo(SOME_CITY.getAdmin2());
            assertThat(cityEntity.getAdmin3()).isEqualTo(SOME_CITY.getAdmin3());
            assertThat(cityEntity.getAdmin4()).isEqualTo(SOME_CITY.getAdmin4());
            assertThat(cityEntity.getPopulation()).isEqualTo(SOME_CITY.getPopulation());
            assertThat(cityEntity.getElevation()).isEqualTo(SOME_CITY.getElevation());
            assertThat(cityEntity.getCc2()).isEqualTo(SOME_CITY.getCc2());
            assertThat(cityEntity.getDem()).isEqualTo(SOME_CITY.getDem());
            assertThat(cityEntity.getTimeZone()).isEqualTo(SOME_CITY.getTimeZone());
            assertThat(cityEntity.getModifiedAt()).isEqualTo(SOME_CITY.getModifiedAt());
        }
    }

    @Nested
    @DisplayName("when mapping from entity")
    class WhenMappingFromEntity {

        @Test
        @DisplayName("it should return mapped city")
        void itShouldReturnMappedCity() {
            City city = cityEntityMapper.fromEntity(SOME_CITY_ENTITY);

            assertThat(city.getIdentifier()).isEqualTo(SOME_CITY_ENTITY.getIdentifier());
            assertThat(city.getName()).isEqualTo(SOME_CITY_ENTITY.getName());
            assertThat(city.getAscii()).isEqualTo(SOME_CITY_ENTITY.getAscii());
            assertThat(city.getAltNames()).isEqualTo(SOME_CITY_ENTITY.getAltNames());
            assertThat(city.getLatitude()).isEqualTo(SOME_CITY_ENTITY.getLatitude());
            assertThat(city.getLongitude()).isEqualTo(SOME_CITY_ENTITY.getLongitude());
            assertThat(city.getFeatClass()).isEqualTo(SOME_CITY_ENTITY.getFeatClass());
            assertThat(city.getFeatCode()).isEqualTo(SOME_CITY_ENTITY.getFeatCode());
            assertThat(city.getCountry()).isEqualTo(SOME_CITY_ENTITY.getCountry());
            assertThat(city.getAdmin1()).isEqualTo(SOME_CITY_ENTITY.getAdmin1());
            assertThat(city.getAdmin2()).isEqualTo(SOME_CITY_ENTITY.getAdmin2());
            assertThat(city.getAdmin3()).isEqualTo(SOME_CITY_ENTITY.getAdmin3());
            assertThat(city.getAdmin4()).isEqualTo(SOME_CITY_ENTITY.getAdmin4());
            assertThat(city.getPopulation()).isEqualTo(SOME_CITY_ENTITY.getPopulation());
            assertThat(city.getElevation()).isEqualTo(SOME_CITY_ENTITY.getElevation());
            assertThat(city.getCc2()).isEqualTo(SOME_CITY_ENTITY.getCc2());
            assertThat(city.getDem()).isEqualTo(SOME_CITY_ENTITY.getDem());
            assertThat(city.getTimeZone()).isEqualTo(SOME_CITY_ENTITY.getTimeZone());
            assertThat(city.getModifiedAt()).isEqualTo(SOME_CITY_ENTITY.getModifiedAt());
        }
    }
}
