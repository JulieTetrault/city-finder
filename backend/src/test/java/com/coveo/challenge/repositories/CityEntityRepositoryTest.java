package com.coveo.challenge.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.coveo.challenge.repositories.entities.CityEntity;
import com.coveo.challenge.testUtils.fixtures.CityEntityFixture;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("CityEntityRepository")
public class CityEntityRepositoryTest {
    private static final String SOME_NAME_FILTER = "new";
    private static final double SOME_LATITUDE_FILTER = 30.7128;
    private static final double SOME_LONGITUDE_FILTER = -54.0060;
    private static final int SOME_PAGE = 0;
    private static final int SOME_PAGE_SIZE = 1;
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


    @Autowired
    private CityEntityRepository cityEntityRepository;

    @BeforeEach
    void setUp() {
        cityEntityRepository.saveAll(List.of(SOME_FIRST_CITY_ENTITY, SOME_SECOND_CITY_ENTITY, SOME_THIRD_CITY_ENTITY));
    }

    @Nested
    @DisplayName("when finding by filters")
    class WhenFindingByFilters {

        @Test
        @DisplayName("it should find entities matching filters")
        void shouldFindEntitiesMatchingFilters() {
            System.out.println(cityEntityRepository.findAll().size());
            List<CityEntity> entities = cityEntityRepository.findByFilters(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER);

            assertThat(entities.size()).isEqualTo(1);
            assertThat(entities.get(0).getName()).isEqualTo("New York");
        }
    }

    @Nested
    @DisplayName("when finding page by filters")
    class WhenFindingPageByFilters {

        @Test
        @DisplayName("it should find page matching filters")
        void shouldFindPagesMatchingFilters() {
            Page<CityEntity> page = cityEntityRepository.findPageByFilters(SOME_NAME_FILTER, SOME_LATITUDE_FILTER, SOME_LONGITUDE_FILTER, SOME_PAGE_REQUEST);

            assertThat(page.getNumber()).isEqualTo(0);
            assertThat(page.getNumberOfElements()).isEqualTo(1);
            assertThat(page.getTotalPages()).isEqualTo(1);
            assertThat(page.getContent().get(0).getName()).isEqualTo("New York");
        }
    }
}
