package com.coveo.challenge.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "coveo.service.challenge.geolocation")
public class GeolocationProperties {
    private String defaultLatitude;
    private String defaultLongitude;
    private String latitudeRange;
    private String longitudeRange;

    public double getDefaultLatitude() {
        return Double.parseDouble(defaultLatitude);
    }

    public double getDefaultLongitude() {
        return Double.parseDouble(defaultLongitude);
    }

    public double getLatitudeRange() {
        return Double.parseDouble(latitudeRange);
    }

    public double getLongitudeRange() {
        return Double.parseDouble(longitudeRange);
    }
}
