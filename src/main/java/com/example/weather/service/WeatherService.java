package com.example.weather.service;

import com.example.weather.dto.DailyForecast;
import com.example.weather.dto.ForecastResponse;
import com.example.weather.dto.WeatherResponse;
import com.example.weather.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class WeatherService {

    private static final Map<String, String> CITIES = new HashMap<>();
    private static final Map<String, String[]> CITY_WEATHER = new HashMap<>();

    static {
        CITIES.put("new york", "US");
        CITIES.put("london", "GB");
        CITIES.put("tokyo", "JP");
        CITIES.put("paris", "FR");
        CITIES.put("madrid", "ES");
        CITIES.put("berlin", "DE");
        CITIES.put("sydney", "AU");
        CITIES.put("buenos aires", "AR");

        CITY_WEATHER.put("new york", new String[]{"Partly cloudy", "01d"});
        CITY_WEATHER.put("london", new String[]{"Rainy", "10d"});
        CITY_WEATHER.put("tokyo", new String[]{"Clear sky", "01d"});
        CITY_WEATHER.put("paris", new String[]{"Cloudy", "03d"});
        CITY_WEATHER.put("madrid", new String[]{"Sunny", "01d"});
        CITY_WEATHER.put("berlin", new String[]{"Overcast", "04d"});
        CITY_WEATHER.put("sydney", new String[]{"Clear sky", "01d"});
        CITY_WEATHER.put("buenos aires", new String[]{"Partly cloudy", "02d"});
    }

    private final Random random = new Random();

    public WeatherResponse getCurrentWeather(String city) {
        String normalizedCity = city.toLowerCase().trim();
        
        if (!CITIES.containsKey(normalizedCity)) {
            throw new CityNotFoundException(city);
        }

        String country = CITIES.get(normalizedCity);
        String[] weather = CITY_WEATHER.get(normalizedCity);
        
        double baseTemp = getBaseTemperature(normalizedCity);
        double temp = baseTemp + (random.nextDouble() * 6 - 3);
        
        return new WeatherResponse(
            capitalize(city),
            country,
            Math.round(temp * 10) / 10.0,
            Math.round((temp + random.nextDouble() * 2) * 10) / 10.0,
            40 + random.nextInt(40),
            weather[0],
            weather[1],
            Instant.now()
        );
    }

    public ForecastResponse getForecast(String city) {
        String normalizedCity = city.toLowerCase().trim();
        
        if (!CITIES.containsKey(normalizedCity)) {
            throw new CityNotFoundException(city);
        }

        String country = CITIES.get(normalizedCity);
        String[] weather = CITY_WEATHER.get(normalizedCity);
        double baseTemp = getBaseTemperature(normalizedCity);

        List<DailyForecast> forecasts = java.util.stream.IntStream.range(0, 5)
            .mapToObj(i -> {
                LocalDate date = LocalDate.now().plusDays(i);
                double tempVariation = random.nextDouble() * 10 - 5;
                double temp = baseTemp + tempVariation;
                
                return new DailyForecast(
                    date,
                    Math.round(temp * 10) / 10.0,
                    Math.round((temp - 2 - random.nextDouble() * 3) * 10) / 10.0,
                    Math.round((temp + 2 + random.nextDouble() * 3) * 10) / 10.0,
                    40 + random.nextInt(40),
                    weather[0],
                    weather[1]
                );
            })
            .toList();

        return new ForecastResponse(capitalize(city), country, forecasts);
    }

    private double getBaseTemperature(String city) {
        return switch (city) {
            case "tokyo", "buenos aires", "sydney" -> 22.0;
            case "madrid", "paris" -> 18.0;
            case "new york" -> 15.0;
            case "london", "berlin" -> 12.0;
            default -> 15.0;
        };
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
