package com.coveo.challenge.repositories;

import static com.coveo.challenge.repositories.CityRepositoryImpl.PAGE_SIZE;
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
import org.springframework.data.domain.Pageable;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.repositories.mappers.CityMapper;
import com.coveo.challenge.repositories.mappers.PaginatedCityMapper;
import com.coveo.challenge.testUtils.fixtures.CityEntityFixture;
import com.coveo.challenge.testUtils.fixtures.CityFixture;
import com.coveo.challenge.testUtils.fixtures.PaginatedDataFixture;

@ExtendWith(MockitoExtension.class)
@DisplayName("CityRepository")
public class CityRepositoryImplTest {
    private static final String SOME_NAME = "name";
    private static final double SOME_LATITUDE = 10;
    private static final double SOME_LONGITUDE = 20;
    private static final int SOME_PAGE = 1;
    private static final CityEntity SOME_CITY_ENTITY = new CityEntityFixture().withIdentifier(1).build();
    private static final CityEntity SOME_OTHER_CITY_ENTITY = new CityEntityFixture().withIdentifier(2).build();
    private static final City SOME_CITY = new CityFixture().withIdentifier(1).build();
    private static final City SOME_OTHER_CITY = new CityFixture().withIdentifier(2).build();
    private static final List<CityEntity> SOME_CITY_ENTITIES = List.of(SOME_CITY_ENTITY, SOME_OTHER_CITY_ENTITY);
    private static final List<City> SOME_CITIES = List.of(SOME_CITY, SOME_OTHER_CITY);
    private static final Pageable SOME_PAGE_REQUEST = PageRequest.of(SOME_PAGE, PAGE_SIZE);
    private static final Page<CityEntity> SOME_PAGINATED_CITY_ENTITIES = new PageImpl<>(SOME_CITY_ENTITIES, SOME_PAGE_REQUEST, SOME_CITY_ENTITIES.size());
    private static final PaginatedData<City> SOME_PAGINATED_CITIES = new PaginatedDataFixture<City>().withData(SOME_CITIES).build();

    @Mock
    private CityMapper cityMapperMock;

    @Mock
    private PaginatedCityMapper paginatedCityMapperMock;

    @Mock
    private CityEntityRepository cityEntityRepositoryMock;

    @InjectMocks
    private CityRepositoryImpl cityRepository;

    @Nested
    @DisplayName("when finding all cities by filters")
    class WhenFindingAllCities {

        @BeforeEach
        void setUp() {
            when(cityEntityRepositoryMock.findByFilters(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE)).thenReturn(SOME_CITY_ENTITIES);
            when(cityMapperMock.fromEntity(SOME_CITY_ENTITY)).thenReturn(SOME_CITY);
            when(cityMapperMock.fromEntity(SOME_OTHER_CITY_ENTITY)).thenReturn(SOME_OTHER_CITY);
        }

        @Test
        @DisplayName("it should call CityEntityRepository")
        void itShouldCallCityEntityRepository() {
            cityRepository.findAllBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);

            verify(cityEntityRepositoryMock).findByFilters(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);
        }

        @Test
        @DisplayName("it should call CityMapper for each city entity")
        void itShouldCallCityMapperForEachCityEntity() {
            cityRepository.findAllBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);

            verify(cityMapperMock).fromEntity(SOME_CITY_ENTITY);
            verify(cityMapperMock).fromEntity(SOME_OTHER_CITY_ENTITY);
        }

        @Test
        @DisplayName("it should return mapped cities")
        void itShouldReturnMappedCities() {
            List<City> cities = cityRepository.findAllBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE);

            assertThat(cities).isEqualTo(SOME_CITIES);
        }
    }

    @Nested
    @DisplayName("when finding paginated cities")
    class WhenFindingPaginatedCities {

        @BeforeEach
        void setUp() {
            when(cityEntityRepositoryMock.findPageByFilters(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE_REQUEST)).thenReturn(SOME_PAGINATED_CITY_ENTITIES);
            when(paginatedCityMapperMock.fromEntity(SOME_PAGINATED_CITY_ENTITIES)).thenReturn(SOME_PAGINATED_CITIES);
        }

        @Test
        @DisplayName("it should call CityEntityRepository")
        void itShouldCallCityEntityRepository() {
            cityRepository.findPageBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);

            verify(cityEntityRepositoryMock).findPageByFilters(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE_REQUEST);
        }

        @Test
        @DisplayName("it should call PaginatedCityMapper")
        void itShouldCallPaginatedCityMapper() {
            cityRepository.findPageBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);

            verify(paginatedCityMapperMock).fromEntity(SOME_PAGINATED_CITY_ENTITIES);
        }

        @Test
        @DisplayName("it should return mapped paginated cities")
        void itShouldReturnMappedPaginatedData() {
            PaginatedData<City> paginatedCities = cityRepository.findPageBy(SOME_NAME, SOME_LATITUDE, SOME_LONGITUDE, SOME_PAGE);

            assertThat(paginatedCities).isEqualTo(SOME_PAGINATED_CITIES);
        }
    }

    @Nested
    @DisplayName("when saving cities")
    class WhenSavingCities {

        @BeforeEach
        void setUp() {
            when(cityMapperMock.toEntity(SOME_CITY)).thenReturn(SOME_CITY_ENTITY);
            when(cityMapperMock.toEntity(SOME_OTHER_CITY)).thenReturn(SOME_OTHER_CITY_ENTITY);
        }

        @Test
        @DisplayName("it should call CityMapper for each city")
        void itShouldCallCityMapperForEachCity() {
            cityRepository.saveAll(SOME_CITIES);

            verify(cityMapperMock).toEntity(SOME_CITY);
            verify(cityMapperMock).toEntity(SOME_OTHER_CITY);
        }

        @Test
        @DisplayName("it should call CityEntityRepository")
        void itShouldCallCityEntityRepository() {
            cityRepository.saveAll(SOME_CITIES);

            verify(cityEntityRepositoryMock).saveAll(SOME_CITY_ENTITIES);
        }
    }
}
