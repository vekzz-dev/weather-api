package com.example.weather.controller;

import com.example.weather.dto.ForecastResponse;
import com.example.weather.dto.WeatherResponse;
import com.example.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current/{city}")
    public ResponseEntity<WeatherResponse> getCurrentWeather(@PathVariable String city) {
        WeatherResponse response = weatherService.getCurrentWeather(city);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/forecast/{city}")
    public ResponseEntity<ForecastResponse> getForecast(@PathVariable String city) {
        ForecastResponse response = weatherService.getForecast(city);
        return ResponseEntity.ok(response);
    }
}
