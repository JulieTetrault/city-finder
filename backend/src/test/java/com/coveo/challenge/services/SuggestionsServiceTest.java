package com.coveo.challenge.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.CityRepository;
import com.coveo.challenge.testUtils.fixtures.CityFixture;
import com.coveo.challenge.testUtils.fixtures.PaginatedDataFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("SuggestionsService")
public class SuggestionsServiceTest {
    private static final String SOME_NAME = "name";
    private static final double SOME_LATITUDE = 10;
    private static final double SOME_LONGITUDE = 20;
    private static final int SOME_PAGE = 1;
    private static final List<City> SOME_CITIES = List.of(new CityFixture().build(), new CityFixture().build());
    private static final PaginatedData<City> SOME_PAGINATED_CITIES = new PaginatedDataFixture<City>().withData(SOME_CITIES).build();

    @Mock
    private CityRepository cityRepositoryMock;

    @InjectMocks
    private SuggestionsService suggestionsService;

    @Nested
    @DisplayName("when getting cities")
    class WhenGettingCities {

        @BeforeEach
        void setUp() {
            when(cityRepositoryMock.findAllBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE)).thenReturn(SOME_CITIES);
        }

        @Test
        @DisplayName("it should call CityRepository")
        void itShouldCallCityRepository() {
            suggestionsService.getCities(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);

            verify(cityRepositoryMock).findAllBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);
        }

        @Test
        @DisplayName("it should return cities")
        void itShouldReturnCities() {
            List<City> cities = suggestionsService.getCities(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);

            assertThat(cities).isEqualTo(SOME_CITIES);
        }
    }

    @Nested
    @DisplayName("when getting paginated cities")
    class WhenGettingPaginatedCities {

        @BeforeEach
        void setUp() {
            when(cityRepositoryMock.findPageBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE)).thenReturn(SOME_PAGINATED_CITIES);
        }

        @Test
        @DisplayName("it should call CityRepository")
        void itShouldCallCityRepository() {
            suggestionsService.getPaginatedCities(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);

            verify(cityRepositoryMock).findPageBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);
        }

        @Test
        @DisplayName("it should return paginated cities")
        void itShouldReturnCities() {
            PaginatedData<City> paginatedCities = suggestionsService.getPaginatedCities(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);

            assertThat(paginatedCities).isEqualTo(SOME_PAGINATED_CITIES);
        }
    }
}
