# Prueba técnica – QA Automation

| Ejercicio | Tipo | Herramienta | Carpeta |
|---|---|---|---|
| 1 | E2E Web – flujo de compra en saucedemo.com | Serenity BDD + Screenplay + Cucumber | [ejercicio-1-e2e-saucedemo](ejercicio-1-e2e-saucedemo/) |
| 2 | API REST – PetStore | Karate DSL | [ejercicio-2-api-petstore](ejercicio-2-api-petstore/) |

Cada carpeta es un proyecto independiente con su `readme.txt` (pasos de ejecución),
`conclusiones.txt` (hallazgos) y `reportes/` (evidencias).

## Requisitos
Java 17+, Maven 3.8+ y Google Chrome (solo para el ejercicio 1).

## Ejecución rápida
```bash
cd ejercicio-1-e2e-saucedemo && mvn clean verify
cd ejercicio-2-api-petstore && mvn clean test
```

Autor: Juan Moreno
