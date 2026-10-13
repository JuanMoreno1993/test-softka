# Pruebas de performance

| Ejercicio | Descripción | Herramienta | Carpeta |
|---|---|---|---|
| 1 | Prueba de carga del servicio de login (FakeStoreAPI), datos desde CSV, ≥ 20 TPS, p(95) < 1,5 s, error < 3 % | Grafana k6 v1.x | [ejercicio-1-carga-login-k6](ejercicio-1-carga-login-k6/) |
| 2 | Análisis de resultados de una prueba de carga (textSummary + diagrama VUs vs RPS) | Informe Word | [ejercicio-2-analisis-resultados](ejercicio-2-analisis-resultados/) |

## Ejecución rápida (ejercicio 1)

```bash
brew install k6                     # macOS
cd ejercicio-1-carga-login-k6
./ejecutar.sh                       # 25 TPS durante 3 minutos
```

Instrucciones completas en [`ejercicio-1-carga-login-k6/readme.txt`](ejercicio-1-carga-login-k6/readme.txt).
