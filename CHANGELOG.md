# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Weather REST API with Spring Boot 3.2.0
- Current weather endpoint (`GET /api/weather/current/{city}`)
- 5-day forecast endpoint (`GET /api/weather/forecast/{city}`)
- Support for 8 cities (New York, London, Tokyo, Paris, Madrid, Berlin, Sydney, Buenos Aires)
- Unit tests for WeatherService
- Global exception handler with proper error responses
- DTOs using Java records for immutability

### Changed
- Initial project setup with Gradle and Java 17

[Unreleased]: https://github.com/example/weather-api/compare/v0.1.0...HEAD
