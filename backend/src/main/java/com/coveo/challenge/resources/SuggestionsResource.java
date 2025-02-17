package com.coveo.challenge.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.mappers.PaginatedSuggestionsResponseMapper;
import com.coveo.challenge.resources.mappers.SuggestionsResponseMapper;
import com.coveo.challenge.resources.responses.PaginatedSuggestionsResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;
import com.coveo.challenge.services.CityService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Deprecated
@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionsResource {
    private final CityService cityService;
    private final SuggestionsResponseMapper suggestionsResponseMapper;
    private final PaginatedSuggestionsResponseMapper paginatedSuggestionsResponseMapper;

    @Deprecated
    @ResponseBody
    @Operation(summary = "Deprecated: Use /v2/cities instead", deprecated = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuggestionsResponse> getSuggestions(
            @RequestParam(value = "q", required = false) String name,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude) {
        log.warn("Deprecated endpoint /suggestions accessed");
        List<City> cities = cityService.getCities(name, latitude, longitude);
        return ResponseEntity.ok(suggestionsResponseMapper.toResponse(cities));
    }

    @Deprecated
    @ResponseBody
    @Operation(summary = "Deprecated: Use /v2/cities instead", deprecated = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "page")
    public ResponseEntity<PaginatedSuggestionsResponse> getPaginatedSuggestions(
            @RequestParam(value = "q", required = false) String name,
            @RequestParam(value = "latitude", required = false) Double latitude,
            @RequestParam(value = "longitude", required = false) Double longitude,
            @RequestParam(value = "page") int page) {
        log.warn("Deprecated endpoint /suggestions accessed");
        PaginatedData<City> paginatedCities = cityService.getPaginatedCities(name, latitude, longitude, page);
        return ResponseEntity.ok(paginatedSuggestionsResponseMapper.toResponse(paginatedCities));
    }
}
