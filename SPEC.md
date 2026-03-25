# Weather API - Specification

## Project Overview

- **Project Name**: weather-api
- **Type**: REST API with Spring Boot 3
- **Core Functionality**: Provide weather information for cities, with support for current weather and forecasts
- **Target Users**: Developers needing weather data for their applications

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Spring Web
- Spring Validation
- Lombok
- Gradle (wrapper)

## Functionality Specification

### Core Features

1. **Get Current Weather**
   - Endpoint: `GET /api/weather/current/{city}`
   - Returns current temperature, humidity, description, and weather icon
   - Query parameter: `city` (required)

2. **Get Weather Forecast**
   - Endpoint: `GET /api/weather/forecast/{city}`
   - Returns 5-day forecast with daily weather data
   - Query parameter: `city` (required)

3. **Supported Cities**
   - Hardcoded list: New York, London, Tokyo, Paris, Madrid, Berlin, Sydney, Buenos Aires

### Data Model

**WeatherResponse**
- city: String
- country: String
- temperature: Double
- feelsLike: Double
- humidity: Integer
- description: String
- icon: String
- timestamp: Instant

**ForecastResponse**
- city: String
- country: String
- forecasts: List<DailyForecast>

**DailyForecast**
- date: LocalDate
- temperature: Double
- minTemp: Double
- maxTemp: Double
- humidity: Integer
- description: String
- icon: String

### Error Handling

- 404: City not found or not supported
- 400: Invalid request parameters
- 500: Internal server error

## Acceptance Criteria

1. Application starts successfully on port 8080
2. GET /api/weather/current/{city} returns valid weather data
3. GET /api/weather/forecast/{city} returns 5-day forecast
4. Invalid city returns 404 error
5. Proper JSON response format
6. Unit tests for service layer
