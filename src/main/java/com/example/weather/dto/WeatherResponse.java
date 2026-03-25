package com.example.weather.dto;

import java.time.Instant;

public record WeatherResponse(
    String city,
    String country,
    double temperature,
    double feelsLike,
    int humidity,
    String description,
    String icon,
    Instant timestamp
) {}
