package com.example.weather.dto;

import java.util.List;

public record ForecastResponse(
    String city,
    String country,
    List<DailyForecast> forecasts
) {}
