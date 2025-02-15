package com.coveo.challenge.utils;

import java.io.InputStream;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FileReader {
    private final ResourceLoader resourceLoader;

    public InputStream read(String filePath) {
        try {
            return resourceLoader.getResource("classpath:" + filePath).getInputStream();
        } catch (Exception e) {
            throw new FileException("Failed to read file", e);
        }
    }
}
