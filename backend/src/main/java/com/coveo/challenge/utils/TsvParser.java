package com.coveo.challenge.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TsvParser {
    public List<String[]> parse(InputStream stream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            reader.readLine();
            String line = reader.readLine();
            List<String[]> records = new ArrayList<>();

            while (line != null) {
                String[] fields = line.split("\t");
                records.add(fields);
                line = reader.readLine();
            }

            reader.close();
            return records;
        } catch (Exception e) {
            throw new TsvException("Failed to parse TSV file", e);
        }
    }
}
