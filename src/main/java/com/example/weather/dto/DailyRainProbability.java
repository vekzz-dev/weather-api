package com.example.weather.dto;

import java.time.LocalDate;

public record DailyRainProbability(
    LocalDate date,
    double probability,
    String description
) {}
