package com.coveo.challenge.repositories.mappers;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.testUtils.fixtures.CityEntityFixture;
import com.coveo.challenge.testUtils.fixtures.CityFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaginatedCityMapper")
public class PaginatedCityMapperTest {
    private static final int SOME_PAGE = 1;
    private static final int SOME_PAGE_SIZE = 3;
    private static final CityEntity SOME_CITY_ENTITY = new CityEntityFixture().withIdentifier(1).build();
    private static final CityEntity SOME_OTHER_CITY_ENTITY = new CityEntityFixture().withIdentifier(2).build();
    private static final City SOME_CITY = new CityFixture().withIdentifier(1).build();
    private static final City SOME_OTHER_CITY = new CityFixture().withIdentifier(2).build();
    private static final List<CityEntity> SOME_CITY_ENTITIES = List.of(SOME_CITY_ENTITY, SOME_OTHER_CITY_ENTITY);
    private static final List<City> SOME_CITIES = List.of(SOME_CITY, SOME_OTHER_CITY);
    private static final Page<CityEntity> SOME_PAGINATED_CITY_ENTITIES = new PageImpl<>(SOME_CITY_ENTITIES, PageRequest.of(SOME_PAGE, SOME_PAGE_SIZE), SOME_CITY_ENTITIES.size());

    @Mock
    private CityMapper cityMapperMock;

    @InjectMocks
    private PaginatedCityMapper paginatedCityMapper;

    @Nested
    @DisplayName("when mapping from entity")
    class WhenMappingFromEntity {

        @BeforeEach
        void setUp() {
            when(cityMapperMock.fromEntity(SOME_CITY_ENTITY)).thenReturn(SOME_CITY);
            when(cityMapperMock.fromEntity(SOME_OTHER_CITY_ENTITY)).thenReturn(SOME_OTHER_CITY);
        }

        @Test
        @DisplayName("it should call CityMapper for each city entity")
        void itShouldCallCityMapperForEachCityEntity() {
             paginatedCityMapper.fromEntity(SOME_PAGINATED_CITY_ENTITIES);

            verify(cityMapperMock).fromEntity(SOME_CITY_ENTITY);
            verify(cityMapperMock).fromEntity(SOME_OTHER_CITY_ENTITY);
        }

        @Test
        @DisplayName("it should return mapped paginated cities")
        void itShouldReturnMappedPaginatedCities() {
            PaginatedData<City> paginatedCities = paginatedCityMapper.fromEntity(SOME_PAGINATED_CITY_ENTITIES);

            assertThat(paginatedCities.getPage()).isEqualTo(SOME_PAGINATED_CITY_ENTITIES.getNumber());
            assertThat(paginatedCities.getTotalNumberOfPages()).isEqualTo(SOME_PAGINATED_CITY_ENTITIES.getTotalPages());
            assertThat(paginatedCities.getData()).isEqualTo(SOME_CITIES);
        }
    }
}
