package com.coveo.challenge.repositories.mappers;

import org.springframework.stereotype.Component;

import com.coveo.challenge.models.City;
import com.coveo.challenge.repositories.entities.CityEntity;

@Component
public class CityMapper {
    public City fromEntity(CityEntity cityEntity) {
        City city = new City();

        city.setIdentifier(cityEntity.getIdentifier());
        city.setName(cityEntity.getName());
        city.setAscii(cityEntity.getAscii());
        city.setAltNames(cityEntity.getAltNames());
        city.setLatitude(cityEntity.getLatitude());
        city.setLongitude(cityEntity.getLongitude());
        city.setFeatClass(cityEntity.getFeatClass());
        city.setFeatCode(cityEntity.getFeatCode());
        city.setCountry(cityEntity.getCountry());
        city.setCc2(cityEntity.getCc2());
        city.setAdmin1(cityEntity.getAdmin1());
        city.setAdmin2(cityEntity.getAdmin2());
        city.setAdmin3(cityEntity.getAdmin3());
        city.setAdmin4(cityEntity.getAdmin4());
        city.setPopulation(cityEntity.getPopulation());
        city.setElevation(cityEntity.getElevation());
        city.setDem(cityEntity.getDem());
        city.setTimeZone(cityEntity.getTimeZone());
        city.setModifiedAt(cityEntity.getModifiedAt());

        return city;
    }

    public CityEntity toEntity(City city) {
        CityEntity cityEntity = new CityEntity();

        cityEntity.setIdentifier(city.getIdentifier());
        cityEntity.setName(city.getName());
        cityEntity.setAscii(city.getAscii());
        cityEntity.setAltNames(city.getAltNames());
        cityEntity.setLatitude(city.getLatitude());
        cityEntity.setLongitude(city.getLongitude());
        cityEntity.setFeatClass(city.getFeatClass());
        cityEntity.setFeatCode(city.getFeatCode());
        cityEntity.setCountry(city.getCountry());
        cityEntity.setCc2(city.getCc2());
        cityEntity.setAdmin1(city.getAdmin1());
        cityEntity.setAdmin2(city.getAdmin2());
        cityEntity.setAdmin3(city.getAdmin3());
        cityEntity.setAdmin4(city.getAdmin4());
        cityEntity.setPopulation(city.getPopulation());
        cityEntity.setElevation(city.getElevation());
        cityEntity.setDem(city.getDem());
        cityEntity.setTimeZone(city.getTimeZone());
        cityEntity.setModifiedAt(city.getModifiedAt());

        return cityEntity;
    }
}
