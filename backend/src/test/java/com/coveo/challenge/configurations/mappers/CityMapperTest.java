package com.coveo.challenge.configurations.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import com.coveo.challenge.models.City;

@ExtendWith(MockitoExtension.class)
@DisplayName("CityMapper")
class CityMapperTest {
    private static final String[] VALID_CITY_RECORD_WITH_ELEVATION = {
            "1", "New York", "New York", "NY,Big Apple",
            "40.7128", "-74.0060", "P", "PPL", "US", "cc2",
            "36", "81", "12", "01", "8419600", "10",
            "10", "America/New_York", "2021-01-01"
    };
    private static final String[] VALID_CITY_RECORD_WITHOUT_ELEVATION = {
            "1", "New York", "New York", "NY,Big Apple",
            "40.7128", "-74.0060", "P", "PPL", "US", "cc2",
            "36", "81", "12", "01", "8419600", null,
            "10", "America/New_York", "2021-01-01"
    };
    private static final String[] INVALID_CITY_RECORD = {
            "1", "New York", "New York", "NY,Big Apple",
            null, null, "P", "PPL", "US", "cc2",
            "36", "81", "12", "01", "8419600", "10",
            "10", "America/New_York", "2021-01-01"
    };

    @InjectMocks
    private CityMapper cityMapper;

    @Nested
    @DisplayName("when mapping from valid record with elevation")
    class WhenMappingFromValidRecordWithElevation {

        @Test
        @DisplayName("it should return mapped city")
        void itShouldReturnMappedCity() {
            Optional<City> optionalCity = cityMapper.fromRecord(VALID_CITY_RECORD_WITH_ELEVATION);

            assertThat(optionalCity).isPresent();
            City city = optionalCity.get();
            assertThat(city.getIdentifier()).isEqualTo(1);
            assertThat(city.getName()).isEqualTo("New York");
            assertThat(city.getAscii()).isEqualTo("New York");
            assertThat(city.getAltNames()).isEqualTo(List.of("NY", "Big Apple"));
            assertThat(city.getLatitude()).isEqualTo(40.7128f);
            assertThat(city.getLongitude()).isEqualTo(-74.0060f);
            assertThat(city.getFeatClass()).isEqualTo("P");
            assertThat(city.getFeatCode()).isEqualTo("PPL");
            assertThat(city.getCountry()).isEqualTo("US");
            assertThat(city.getAdmin1()).isEqualTo("36");
            assertThat(city.getAdmin2()).isEqualTo("81");
            assertThat(city.getAdmin3()).isEqualTo("12");
            assertThat(city.getAdmin4()).isEqualTo("01");
            assertThat(city.getPopulation()).isEqualTo(8419600);
            assertThat(city.getElevation()).isEqualTo(10);
            assertThat(city.getCc2()).isEqualTo("cc2");
            assertThat(city.getDem()).isEqualTo("10");
            assertThat(city.getTimeZone()).isEqualTo("America/New_York");
            assertThat(city.getModifiedAt()).isEqualTo("2021-01-01");
        }
    }

    @Nested
    @DisplayName("when mapping from valid record without elevation")
    class WhenMappingFromValidRecordWithoutElevation {

        @Test
        @DisplayName("it should return mapped city with default elevation")
        void itShouldReturnMappedCityWithDefaultElevation() {
            Optional<City> optionalCity = cityMapper.fromRecord(VALID_CITY_RECORD_WITHOUT_ELEVATION);

            assertThat(optionalCity).isPresent();
            City city = optionalCity.get();
            assertThat(city.getIdentifier()).isEqualTo(1);
            assertThat(city.getName()).isEqualTo("New York");
            assertThat(city.getAscii()).isEqualTo("New York");
            assertThat(city.getAltNames()).isEqualTo(List.of("NY", "Big Apple"));
            assertThat(city.getLatitude()).isEqualTo(40.7128f);
            assertThat(city.getLongitude()).isEqualTo(-74.0060f);
            assertThat(city.getFeatClass()).isEqualTo("P");
            assertThat(city.getFeatCode()).isEqualTo("PPL");
            assertThat(city.getCountry()).isEqualTo("US");
            assertThat(city.getAdmin1()).isEqualTo("36");
            assertThat(city.getAdmin2()).isEqualTo("81");
            assertThat(city.getAdmin3()).isEqualTo("12");
            assertThat(city.getAdmin4()).isEqualTo("01");
            assertThat(city.getPopulation()).isEqualTo(8419600);
            assertThat(city.getElevation()).isEqualTo(-1L);
            assertThat(city.getCc2()).isEqualTo("cc2");
            assertThat(city.getDem()).isEqualTo("10");
            assertThat(city.getTimeZone()).isEqualTo("America/New_York");
            assertThat(city.getModifiedAt()).isEqualTo("2021-01-01");
        }
    }

    @Nested
    @DisplayName("when mapping from invalid record")
    class WhenMappingFromInvalidRecord {

        @Test
        @DisplayName("it should return empty value")
        void itShouldReturnEmptyValue() {
            Optional<City> optionalCity = cityMapper.fromRecord(INVALID_CITY_RECORD);

            assertThat(optionalCity).isEmpty();
        }
    }
}
