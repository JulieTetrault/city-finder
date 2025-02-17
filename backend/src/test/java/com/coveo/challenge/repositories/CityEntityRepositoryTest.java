package com.coveo.challenge.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coveo.challenge.configurations.GeolocationProperties;
import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.repositories.specifications.CitySpecificationBuilder;
import com.coveo.challenge.testUtils.fixtures.CityEntityFixture;

import jakarta.transaction.Transactional;

@DataJpaTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@DisplayName("CityEntityRepository")
public class CityEntityRepositoryTest {
    private static final String SOME_NAME_FILTER = "new";
    private static final double SOME_LATITUDE_FILTER = 30.7128;
    private static final double SOME_LONGITUDE_FILTER = -54.0060;
    private static final int SOME_PAGE = 0;
    private static final int SOME_PAGE_SIZE = 2;
    private static final Pageable SOME_PAGE_REQUEST = PageRequest.of(SOME_PAGE, SOME_PAGE_SIZE);
    private static final CityEntity SOME_FIRST_CITY_ENTITY = new CityEntityFixture()
            .withName("New York")
            .withLatitude(40.7128)
            .withLongitude(-74.0060)
            .build();
    private static final CityEntity SOME_SECOND_CITY_ENTITY = new CityEntityFixture()
            .withName("New Orleans")
            .withLatitude(29.9511)
            .withLongitude(-90.0715)
            .build();
    private static final CityEntity SOME_THIRD_CITY_ENTITY = new CityEntityFixture()
            .withName("San Francisco")
            .withLatitude(37.7749)
            .withLongitude(-122.4194)
            .build();
    private static final CityEntity SOME_FOURTH_CITY_ENTITY = new CityEntityFixture()
            .withName("Newark")
            .withLatitude(37.5295)
            .withLongitude(-122.0400)
            .build();

    @Autowired
    private CityEntityRepository cityEntityRepository;

    private CitySpecificationBuilder citySpecificationBuilder;

    @BeforeAll
    void setUp() {
        GeolocationProperties geolocationProperties = new GeolocationProperties();
        geolocationProperties.setDefaultLatitude("40.0");
        geolocationProperties.setDefaultLongitude("-130.0");
        geolocationProperties.setLatitudeRange("10.0");
        geolocationProperties.setLongitudeRange("20.0");

        citySpecificationBuilder = new CitySpecificationBuilder(geolocationProperties);
        cityEntityRepository.saveAll(List.of(SOME_FIRST_CITY_ENTITY, SOME_SECOND_CITY_ENTITY, SOME_THIRD_CITY_ENTITY, SOME_FOURTH_CITY_ENTITY));
    }

    @Nested
    @DisplayName("when finding all by filters")
    class WhenFindingAllByFilters {

        @Nested
        @DisplayName("given no filters")
        class GivenNoFilters {

            @Test
            @DisplayName("it should find entities matching default geolocation properties")
            void shouldFindEntitiesMatchingDefaultGeolocationProperties() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(null, null, null);
                List<CityEntity> entities = cityEntityRepository.findAll(specification);

                assertThat(entities.size()).isEqualTo(2);
                assertThat(entities).extracting(CityEntity::getName).contains("San Francisco");
                assertThat(entities).extracting(CityEntity::getName).contains("Newark");
            }
        }

        @Nested
        @DisplayName("given only name filter")
        class GivenOnlyNameFilter {

            @Test
            @DisplayName("it should find entities matching name filter and default geolocation properties")
            void shouldFindEntitiesMatchingNameFilterAndDefaultGeolocationProperties() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(SOME_NAME_FILTER, null, null);
                List<CityEntity> entities = cityEntityRepository.findAll(specification);

                assertThat(entities.size()).isEqualTo(1);
                assertThat(entities).extracting(CityEntity::getName).contains("Newark");
            }
        }

        @Nested
        @DisplayName("given all filters")
        class GivenNameAndLatitudeFilter {

            @Test
            @DisplayName("it should find entities matching filters")
            void shouldFindEntitiesMatchingFilters() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);
                List<CityEntity> entities = cityEntityRepository.findAll(specification);

                assertThat(entities.size()).isEqualTo(1);
                assertThat(entities).extracting(CityEntity::getName).contains("New York");
            }
        }
    }

    @Nested
    @DisplayName("when finding all by filters with pagination")
    class WhenFindingAllByFiltersWithPagination {

        @Nested
        @DisplayName("given no filters")
        class GivenNoFilters {

            @Test
            @DisplayName("it should find paginated entities matching default geolocation properties")
            void shouldFindPaginatedEntitiesMatchingDefaultGeolocationProperties() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(null, null, null);
                Page<CityEntity> page = cityEntityRepository.findAll(specification, SOME_PAGE_REQUEST);

                assertThat(page.getNumber()).isEqualTo(0);
                assertThat(page.getNumberOfElements()).isEqualTo(2);
                assertThat(page.getTotalPages()).isEqualTo(1);
                assertThat(page.getContent()).extracting(CityEntity::getName).contains("San Francisco");
                assertThat(page.getContent()).extracting(CityEntity::getName).contains("Newark");
            }
        }

        @Nested
        @DisplayName("given only name filter")
        class GivenOnlyNameFilter {

            @Test
            @DisplayName("it should find paginated entities matching name filter and default geolocation properties")
            void shouldFindPaginatedEntitiesMatchingNameFilterAndDefaultGeolocationProperties() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(SOME_NAME_FILTER, null, null);
                Page<CityEntity> page = cityEntityRepository.findAll(specification, SOME_PAGE_REQUEST);

                assertThat(page.getNumber()).isEqualTo(0);
                assertThat(page.getNumberOfElements()).isEqualTo(1);
                assertThat(page.getTotalPages()).isEqualTo(1);
                assertThat(page.getContent()).extracting(CityEntity::getName).contains("Newark");
            }
        }

        @Nested
        @DisplayName("given all filters")
        class GivenNameAndLatitudeFilter {

            @Test
            @DisplayName("it should find paginated entities matching filters")
            void shouldFindPaginatedEntitiesMatchingFilters() {
                Specification<CityEntity> specification = citySpecificationBuilder.build(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);
                Page<CityEntity> page = cityEntityRepository.findAll(specification, SOME_PAGE_REQUEST);

                assertThat(page.getNumber()).isEqualTo(0);
                assertThat(page.getNumberOfElements()).isEqualTo(1);
                assertThat(page.getTotalPages()).isEqualTo(1);
                assertThat(page.getContent()).extracting(CityEntity::getName).contains("New York");
            }
        }
    }
}
