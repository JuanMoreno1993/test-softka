# PetStore API – Pruebas de servicios REST con Karate

Pruebas automatizadas de la API [petstore.swagger.io](https://petstore.swagger.io/) con **Karate DSL** (Java 17, Maven).

| Caso | Operación | Endpoint |
|---|---|---|
| 1 | Añadir una mascota | `POST /pet` |
| 2 | Consultar por ID | `GET /pet/{petId}` |
| 3 | Actualizar nombre y estatus a `sold` | `PUT /pet` |
| 4 | Consultar por estatus | `GET /pet/findByStatus?status=sold` |

## Ejecución rápida

```bash
mvn clean test
```

Reporte: `target/karate-reports/karate-summary.html`

- Instrucciones paso a paso: [`readme.txt`](readme.txt)
- Casos, hallazgos y conclusiones: [`conclusiones.txt`](conclusiones.txt)
- Evidencias de ejecución: [`reportes/`](reportes/)
