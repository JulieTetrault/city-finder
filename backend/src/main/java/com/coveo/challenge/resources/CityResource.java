package com.coveo.challenge.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.mappers.CityResponseMapper;
import com.coveo.challenge.resources.mappers.PaginatedSearchedCitiesResponseMapper;
import com.coveo.challenge.resources.mappers.SearchedCitiesResponseMapper;
import com.coveo.challenge.resources.responses.CityResponse;
import com.coveo.challenge.resources.responses.PaginatedSearchedCitiesResponse;
import com.coveo.challenge.resources.responses.SearchedCitiesResponse;
import com.coveo.challenge.services.CityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v2/cities")
@RequiredArgsConstructor
public class CityResource {
    private final CityService cityService;
    private final CityResponseMapper cityResponseMapper;
    private final SearchedCitiesResponseMapper searchedCitiesResponseMapper;
    private final PaginatedSearchedCitiesResponseMapper paginatedSearchedCitiesResponseMapper;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CityResponse> getCity(@PathVariable long id) {
        City city = cityService.getCity(id);
        return ResponseEntity.ok(cityResponseMapper.toResponse(city));
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SearchedCitiesResponse> searchCities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude) {
        List<City> cities = cityService.getCities(name, latitude, longitude);
        return ResponseEntity.ok(searchedCitiesResponseMapper.toResponse(cities));
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, params = "page")
    @ResponseBody
    public ResponseEntity<PaginatedSearchedCitiesResponse> searchPaginatedCities(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude,
            @RequestParam(value = "page") int page) {
        PaginatedData<City> paginatedCities = cityService.getPaginatedCities(name, latitude, longitude, page);
        return ResponseEntity.ok(paginatedSearchedCitiesResponseMapper.toResponse(paginatedCities));
    }
}
