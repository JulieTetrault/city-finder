package com.coveo.challenge.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.mappers.PaginatedSuggestionsResponseMapper;
import com.coveo.challenge.resources.mappers.SuggestionsResponseMapper;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.PaginatedSuggestionsResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;
import com.coveo.challenge.services.CityService;
import com.coveo.challenge.testUtils.fixtures.CityFixture;
import com.coveo.challenge.testUtils.fixtures.CityResponseFixture;
import com.coveo.challenge.testUtils.fixtures.PaginatedDataFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("SuggestionsResource")
public class SuggestionsResourceTest
{
    private static final String SOME_NAME_FILTER = "new";
    private static final double SOME_LATITUDE_FILTER = 30.7128;
    private static final double SOME_LONGITUDE_FILTER = -54.0060;
    private static final int SOME_PAGE = 0;
    private static final List<City> SOME_CITIES = List.of(new CityFixture().build());
    private static final PaginatedData<City> SOME_PAGINATED_CITIES = new PaginatedDataFixture<City>().withData(SOME_CITIES).build();
    private static final List<CityResponse> SOME_CITY_RESPONSES = List.of(new CityResponseFixture().build());
    private static final SuggestionsResponse SOME_SUGGESTIONS_RESPONSE = SuggestionsResponse.builder().cities(SOME_CITY_RESPONSES).build();
    private static final PaginatedSuggestionsResponse SOME_PAGINATED_SUGGESTIONS_RESPONSE = PaginatedSuggestionsResponse.builder()
            .page(SOME_PAGE)
            .totalNumberOfPages(1)
            .cities(SOME_CITY_RESPONSES)
            .build();

    @Mock
    private CityService cityServiceMock;

    @Mock
    private SuggestionsResponseMapper suggestionsResponseMapperMock;

    @Mock
    private PaginatedSuggestionsResponseMapper paginatedSuggestionsResponseMapperMock;

    @InjectMocks
    private SuggestionsResource suggestionsResource;

    @Nested
    @DisplayName("when getting suggestions")
    class WheGettingSuggestions {

        @BeforeEach
        void setUp() {
            when(cityServiceMock.getCities(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER)).thenReturn(SOME_CITIES);
            when(suggestionsResponseMapperMock.toResponse(SOME_CITIES)).thenReturn(SOME_SUGGESTIONS_RESPONSE);
        }

        @Test
        @DisplayName("it should call CityService")
        void itShouldCallCityService() {
            suggestionsResource.getSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);

            verify(cityServiceMock).getCities(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);
        }

        @Test
        @DisplayName("it should call SuggestionsMapper")
        void itShouldCallSuggestionsMapper() {
            suggestionsResource.getSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);

            verify(suggestionsResponseMapperMock).toResponse(SOME_CITIES);
        }

        @Test
        @DisplayName("it should return suggestions response")
        void itShouldReturnSuggestionsResponse() {
            ResponseEntity<SuggestionsResponse> suggestionsResponse = suggestionsResource.getSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);

            assertThat(suggestionsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(suggestionsResponse.getBody()).isEqualTo(SOME_SUGGESTIONS_RESPONSE);
        }
    }

    @Nested
    @DisplayName("when getting paginated suggestions")
    class WheGettingPaginatedSuggestions {

        @BeforeEach
        void setUp() {
            when(cityServiceMock.getPaginatedCities(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE)).thenReturn(SOME_PAGINATED_CITIES);
            when(paginatedSuggestionsResponseMapperMock.toResponse(SOME_PAGINATED_CITIES)).thenReturn(SOME_PAGINATED_SUGGESTIONS_RESPONSE);
        }

        @Test
        @DisplayName("it should call CityService")
        void itShouldCallCityService() {
            suggestionsResource.getPaginatedSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE);

            verify(cityServiceMock).getPaginatedCities(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE);
        }

        @Test
        @DisplayName("it should call PaginatedSuggestionsResponseMapper")
        void itShouldCallPaginatedSuggestionsResponseMapper() {
            suggestionsResource.getPaginatedSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE);

            verify(paginatedSuggestionsResponseMapperMock).toResponse(SOME_PAGINATED_CITIES);
        }

        @Test
        @DisplayName("it should return paginated suggestions response")
        void itShouldReturnPaginatedSuggestionsResponse() {
            ResponseEntity<PaginatedSuggestionsResponse> paginatedSuggestionsResponse = suggestionsResource.getPaginatedSuggestions(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE);

            assertThat(paginatedSuggestionsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(paginatedSuggestionsResponse.getBody()).isEqualTo(SOME_PAGINATED_SUGGESTIONS_RESPONSE);
        }
    }
}
