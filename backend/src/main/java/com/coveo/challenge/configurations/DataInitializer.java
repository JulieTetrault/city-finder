package com.coveo.challenge.configurations;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.coveo.challenge.configurations.mappers.CityRecordMapper;
import com.coveo.challenge.repositories.CityRepository;
import com.coveo.challenge.utils.FileReader;
import com.coveo.challenge.utils.TsvParser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CityRecordMapper cityMapper;
    private final FileReader fileLoader;
    private final CityRepository cityRepository;

    @Override
    public void run(String... args) {
        InputStream stream = fileLoader.read("data/cities_canada-usa.tsv");
        List<String[]> records = TsvParser.parse(stream);
        cityRepository.saveAll(records.stream().map(cityMapper::fromRecord).flatMap(Optional::stream).toList());
    }
}
