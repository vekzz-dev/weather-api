package com.example.weather.dto;

import java.time.LocalDate;

public record DailyForecast(
    LocalDate date,
    double temperature,
    double minTemp,
    double maxTemp,
    int humidity,
    String description,
    String icon
) {}
