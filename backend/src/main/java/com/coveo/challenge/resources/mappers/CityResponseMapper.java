package com.coveo.challenge.resources.mappers;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;

@Component
public class CityResponseMapper {
    public CityResponse toResponse(City city) {
        CityResponse cityResponse = new CityResponse();

        cityResponse.setId(city.getIdentifier());
        cityResponse.setName(city.getName());
        cityResponse.setAscii(city.getAscii());
        cityResponse.setAltName(String.join(",", city.getAltNames()));
        cityResponse.setLatitude((float) city.getLatitude());
        cityResponse.setLongitude((float) city.getLongitude());
        cityResponse.setFeatClass(city.getFeatClass());
        cityResponse.setFeatCode(city.getFeatCode());
        cityResponse.setCountry(city.getCountry());
        cityResponse.setCc2(city.getCc2());
        cityResponse.setAdmin1(city.getAdmin1());
        cityResponse.setAdmin2(city.getAdmin2());
        cityResponse.setAdmin3(city.getAdmin3());
        cityResponse.setAdmin4(city.getAdmin4());
        cityResponse.setPopulation(city.getPopulation());
        cityResponse.setElevation(city.getElevation());
        cityResponse.setDem(city.getDem());
        cityResponse.setTz(city.getTimeZone());
        cityResponse.setModifiedAt(city.getModifiedAt());

        return cityResponse;
    }
}
