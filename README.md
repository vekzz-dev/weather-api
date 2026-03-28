# Weather API

REST API para obtener información del clima actual, pronósticos de 5 días y probabilidad de lluvia para diferentes ciudades del mundo.

## Características

- Clima actual por ciudad
- Pronóstico de 5 días
- Probabilidad de lluvia a 5 días
- Manejo de errores robusto
- Validación de ciudades soportadas

## Ciudades Soportadas

- New York (US)
- London (GB)
- Tokyo (JP)
- Paris (FR)
- Madrid (ES)
- Berlin (DE)
- Sydney (AU)
- Buenos Aires (AR)

## Uso

### Clima actual

```bash
curl http://localhost:8080/api/weather/current/london
```

Respuesta:
```json
{
  "city": "London",
  "country": "GB",
  "temperature": 9.8,
  "feelsLike": 10.9,
  "humidity": 78,
  "description": "Rainy",
  "icon": "10d",
  "timestamp": "2026-03-25T07:05:48Z"
}
```

### Pronóstico de 5 días

```bash
curl http://localhost:8080/api/weather/forecast/tokyo
```

### Probabilidad de lluvia

```bash
curl http://localhost:8080/api/weather/rain-probability/london
```

Respuesta:
```json
{
  "city": "London",
  "country": "GB",
  "forecasts": [
    { "date": "2026-03-28", "probability": 0.58, "description": "Likely" },
    { "date": "2026-03-29", "probability": 0.71, "description": "Likely" },
    { "date": "2026-03-30", "probability": 0.62, "description": "Likely" },
    { "date": "2026-03-31", "probability": 0.55, "description": "Possible" },
    { "date": "2026-04-01", "probability": 0.69, "description": "Likely" }
  ]
}
```

| Probabilidad | Descripción |
|---|---|
| >= 0.8 | Very likely |
| >= 0.6 | Likely |
| >= 0.4 | Possible |
| >= 0.2 | Unlikely |
| < 0.2 | Very unlikely |

## Ejecución

```bash
./gradlew bootRun
```

La API estará disponible en `http://localhost:8080`

## Ejecutar Tests

```bash
./gradlew test
```

## Construir

```bash
./gradlew build
```

## License

MIT
