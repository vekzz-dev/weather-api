package com.example.weather.dto;

import java.util.List;

public record RainProbabilityResponse(
    String city,
    String country,
    List<DailyRainProbability> forecasts
) {}
