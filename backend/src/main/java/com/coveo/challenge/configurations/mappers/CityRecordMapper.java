package com.coveo.challenge.configurations.mappers;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;

@Component
public class CityRecordMapper {
    private static final long DEFAULT_ELEVATION = -1L;

    public Optional<City> fromRecord(String[] record) {
        try {
            City city = new City();

            city.setIdentifier(Integer.parseInt(record[0]));
            city.setName(record[1]);
            city.setAscii(record[2]);
            city.setAltNames(Arrays.stream(record[3].split(",")).toList());
            city.setLatitude(Float.parseFloat(record[4]));
            city.setLongitude(Float.parseFloat(record[5]));
            city.setFeatClass(record[6]);
            city.setFeatCode(record[7]);
            city.setCountry(record[8]);
            city.setCc2(record[9]);
            city.setAdmin1(record[10]);
            city.setAdmin2(record[11]);
            city.setAdmin3(record[12]);
            city.setAdmin4(record[13]);
            city.setPopulation(Long.parseLong(record[14]));

            try {
                city.setElevation(Long.parseLong(record[15]));
            } catch (NumberFormatException e) {
                city.setElevation(DEFAULT_ELEVATION);
            }

            city.setDem(record[16]);
            city.setTimeZone(record[17]);
            city.setModifiedAt(record[18]);

            return Optional.of(city);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
