# Weather API

REST API para obtener información del clima actual y pronósticos de 5 días para diferentes ciudades del mundo.

## Características

- Clima actual por ciudad
- Pronóstico de 5 días
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
