package com.coveo.challenge.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coveo.challenge.models.City;
import com.coveo.challenge.models.PaginatedData;
import com.coveo.challenge.resources.mappers.PaginatedSuggestionsMapper;
import com.coveo.challenge.resources.mappers.SuggestionsMapper;
import com.coveo.challenge.resources.responses.PaginatedSuggestionsResponse;
import com.coveo.challenge.resources.responses.SuggestionsResponse;
import com.coveo.challenge.services.SuggestionsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class SuggestionsResource
{
    private static final String DEFAULT_LATITUDE = "45.9778182";
    private static final String DEFAULT_LONGITUDE = "-77.8968753";

    private final SuggestionsService suggestionsService;
    private final SuggestionsMapper suggestionsMapper;
    private final PaginatedSuggestionsMapper paginatedSuggestionsMapper;

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SuggestionsResponse> getSuggestions(
            @RequestParam("q") String name,
            @RequestParam(defaultValue = DEFAULT_LATITUDE) Double latitude,
            @RequestParam(defaultValue = DEFAULT_LONGITUDE) Double longitude) {

        System.out.println("Entering getSuggestions: q=" + name + ", latitude=" + latitude + ", longitude=" + longitude);

        List<City> cities = suggestionsService.getCities(name, latitude, longitude);
        return ResponseEntity.ok(suggestionsMapper.toResponse(cities));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "page")
    @ResponseBody
    public ResponseEntity<PaginatedSuggestionsResponse> getPaginatedSuggestions(
            @RequestParam("q") String name,
            @RequestParam(defaultValue = DEFAULT_LATITUDE) Double latitude,
            @RequestParam(defaultValue = DEFAULT_LONGITUDE) Double longitude,
            @RequestParam Integer page) {

        System.out.println("Entering getPaginatedSuggestions: q=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", page=" + page);

        PaginatedData<City> paginatedCities = suggestionsService.getPaginatedCities(name, latitude, longitude, page);
        return ResponseEntity.ok(paginatedSuggestionsMapper.toResponse(paginatedCities));
    }
}
