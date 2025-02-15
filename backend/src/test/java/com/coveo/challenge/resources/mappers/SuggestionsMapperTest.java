package com.coveo.challenge.resources.mappers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;
import com.coveo.challenge.testUtils.fixtures.CityFixture;
import com.coveo.challenge.testUtils.fixtures.CityResponseFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("SuggestionsMapper")
public class SuggestionsMapperTest {
    private static final City SOME_CITY = new CityFixture().withIdentifier(1).build();
    private static final City SOME_OTHER_CITY = new CityFixture().withIdentifier(2).build();
    private static final CityResponse SOME_CITY_RESPONSE = new CityResponseFixture().withId(1).build();
    private static final CityResponse SOME_OTHER_CITY_RESPONSE = new CityResponseFixture().withId(2).build();
    private static final List<City> SOME_CITIES = List.of(SOME_CITY, SOME_OTHER_CITY);
    private static final List<CityResponse> SOME_CITY_RESPONSES = List.of(SOME_CITY_RESPONSE, SOME_OTHER_CITY_RESPONSE);

    @Mock
    private CityMapper cityMapperMock;

    @InjectMocks
    private SuggestionsMapper suggestionsMapper;

    @Nested
    @DisplayName("when mapping to response")
    class WhenMappingToResponse {

        @BeforeEach
        void setUp() {
            when(cityMapperMock.toResponse(SOME_CITY)).thenReturn(SOME_CITY_RESPONSE);
            when(cityMapperMock.toResponse(SOME_OTHER_CITY)).thenReturn(SOME_OTHER_CITY_RESPONSE);
        }

        @Test
        @DisplayName("it should call CityMapper for each city")
        void itShouldCallCityMapperForEachCity() {
            suggestionsMapper.toResponse(SOME_CITIES);

            verify(cityMapperMock).toResponse(SOME_CITY);
            verify(cityMapperMock).toResponse(SOME_OTHER_CITY);
        }

        @Test
        @DisplayName("it should return mapped suggestions response")
        void itShouldReturnMappedSuggestionsResponse() {
            SuggestionsResponse suggestionsResponse = suggestionsMapper.toResponse(SOME_CITIES);

            assertThat(suggestionsResponse.getCities()).isEqualTo(SOME_CITY_RESPONSES);
        }
    }
}
