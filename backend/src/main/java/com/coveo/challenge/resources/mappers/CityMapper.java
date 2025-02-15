package com.coveo.challenge.resources.mappers;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.resources.responses.CityResponse;

@Component
public class CityMapper {
    public CityResponse toResponse(City city) {
        CityResponse cityResponse = new CityResponse();

        cityResponse.setId(city.getIdentifier());
        cityResponse.setName(city.getName());
        cityResponse.setAscii(city.getAscii());
        cityResponse.setAlt_name(String.join(",", city.getAltNames()));
        cityResponse.setLatitude((float)city.getLatitude());
        cityResponse.setLongitude((float)city.getLongitude());
        cityResponse.setFeat_class(city.getFeatClass());
        cityResponse.setFeat_code(city.getFeatCode());
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
        cityResponse.setModified_at(city.getModifiedAt());

        return cityResponse;
    }
}
