package com.coveo.challenge.resources.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.testUtils.fixtures.CityFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("CityResponseMapper")
public class CityResponseMapperTest {
    private static final City SOME_CITY = new CityFixture().build();

    @InjectMocks
    private CityResponseMapper cityResponseMapper;

    @Nested
    @DisplayName("when mapping to response")
    class WhenMappingToResponse {

        @Test
        @DisplayName("it should return mapped city response")
        void itShouldReturnMappedCityResponse() {
            CityResponse cityResponse = cityResponseMapper.toResponse(SOME_CITY);

            assertThat(cityResponse.getId()).isEqualTo(SOME_CITY.getIdentifier());
            assertThat(cityResponse.getName()).isEqualTo(SOME_CITY.getName());
            assertThat(cityResponse.getAscii()).isEqualTo(SOME_CITY.getAscii());
            assertThat(cityResponse.getAltName()).isEqualTo(String.join(",",SOME_CITY.getAltNames()));
            assertThat(cityResponse.getLatitude()).isEqualTo((float)SOME_CITY.getLatitude());
            assertThat(cityResponse.getLongitude()).isEqualTo((float)SOME_CITY.getLongitude());
            assertThat(cityResponse.getFeatClass()).isEqualTo(SOME_CITY.getFeatClass());
            assertThat(cityResponse.getFeatCode()).isEqualTo(SOME_CITY.getFeatCode());
            assertThat(cityResponse.getCountry()).isEqualTo(SOME_CITY.getCountry());
            assertThat(cityResponse.getAdmin1()).isEqualTo(SOME_CITY.getAdmin1());
            assertThat(cityResponse.getAdmin2()).isEqualTo(SOME_CITY.getAdmin2());
            assertThat(cityResponse.getAdmin3()).isEqualTo(SOME_CITY.getAdmin3());
            assertThat(cityResponse.getAdmin4()).isEqualTo(SOME_CITY.getAdmin4());
            assertThat(cityResponse.getPopulation()).isEqualTo(SOME_CITY.getPopulation());
            assertThat(cityResponse.getElevation()).isEqualTo(SOME_CITY.getElevation());
            assertThat(cityResponse.getCc2()).isEqualTo(SOME_CITY.getCc2());
            assertThat(cityResponse.getDem()).isEqualTo(SOME_CITY.getDem());
            assertThat(cityResponse.getTz()).isEqualTo(SOME_CITY.getTimeZone());
            assertThat(cityResponse.getModifiedAt()).isEqualTo(SOME_CITY.getModifiedAt());
        }
    }
}
