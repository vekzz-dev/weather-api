package com.example.weather.service;

import com.example.weather.dto.ForecastResponse;
import com.example.weather.dto.WeatherResponse;
import com.example.weather.exception.CityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService();
    }

    @Test
    void getCurrentWeather_validCity_returnsWeather() {
        WeatherResponse response = weatherService.getCurrentWeather("London");

        assertNotNull(response);
        assertEquals("London", response.city());
        assertEquals("GB", response.country());
        assertNotNull(response.temperature());
        assertNotNull(response.description());
    }

    @Test
    void getCurrentWeather_invalidCity_throwsException() {
        assertThrows(CityNotFoundException.class, () -> {
            weatherService.getCurrentWeather("InvalidCity");
        });
    }

    @Test
    void getForecast_validCity_returnsForecast() {
        ForecastResponse response = weatherService.getForecast("Tokyo");

        assertNotNull(response);
        assertEquals("Tokyo", response.city());
        assertEquals("JP", response.country());
        assertEquals(5, response.forecasts().size());
    }

    @Test
    void getForecast_invalidCity_throwsException() {
        assertThrows(CityNotFoundException.class, () -> {
            weatherService.getForecast("UnknownCity");
        });
    }

    @Test
    void getCurrentWeather_cityNameNormalization_works() {
        WeatherResponse response1 = weatherService.getCurrentWeather("london");
        WeatherResponse response2 = weatherService.getCurrentWeather("LONDON");
        WeatherResponse response3 = weatherService.getCurrentWeather("London");

        assertEquals(response1.city(), response2.city());
        assertEquals(response2.city(), response3.city());
    }
}
