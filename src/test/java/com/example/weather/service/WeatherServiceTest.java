package com.example.weather.service;

import com.example.weather.dto.DailyRainProbability;
import com.example.weather.dto.ForecastResponse;
import com.example.weather.dto.RainProbabilityResponse;
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

    @Test
    void getRainProbability_validCity_returnsRainProbability() {
        RainProbabilityResponse response = weatherService.getRainProbability("London");

        assertNotNull(response);
        assertEquals("London", response.city());
        assertEquals("GB", response.country());
        assertEquals(5, response.forecasts().size());
    }

    @Test
    void getRainProbability_invalidCity_throwsException() {
        assertThrows(CityNotFoundException.class, () -> {
            weatherService.getRainProbability("UnknownCity");
        });
    }

    @Test
    void getRainProbability_probabilitiesInRange() {
        RainProbabilityResponse response = weatherService.getRainProbability("Tokyo");

        for (DailyRainProbability forecast : response.forecasts()) {
            assertTrue(forecast.probability() >= 0.0 && forecast.probability() <= 1.0,
                "Probability should be between 0 and 1");
            assertNotNull(forecast.date());
            assertNotNull(forecast.description());
        }
    }

    @Test
    void getRainProbability_cityNameNormalization_works() {
        RainProbabilityResponse response1 = weatherService.getRainProbability("london");
        RainProbabilityResponse response2 = weatherService.getRainProbability("LONDON");
        RainProbabilityResponse response3 = weatherService.getRainProbability("London");

        assertEquals(response1.city(), response2.city());
        assertEquals(response2.city(), response3.city());
    }
}
