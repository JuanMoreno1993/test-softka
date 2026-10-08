===============================================================================
 PRUEBAS DE SERVICIOS REST - API PETSTORE
 Karate DSL + JUnit 5 + Maven
===============================================================================

1. DESCRIPCIÓN
-------------------------------------------------------------------------------
Pruebas automatizadas de la API pública https://petstore.swagger.io/v2 que
cubren los siguientes casos, en un flujo encadenado:

  Caso 1. Añadir una mascota a la tienda                 POST /pet
  Caso 2. Consultar la mascota creada (búsqueda por ID)   GET  /pet/{petId}
  Caso 3. Actualizar el nombre y el estatus a "sold"      PUT  /pet
  Caso 4. Consultar la mascota por estatus                GET  /pet/findByStatus?status=sold

Adicional: un escenario negativo que valida que consultar un ID inexistente
retorna 404.


2. PRERREQUISITOS
-------------------------------------------------------------------------------
  - Java JDK 17 o superior        Verificar con:  java -version
  - Apache Maven 3.8 o superior   Verificar con:  mvn -v
  - Git
  - Conexión a internet

Instalación en macOS (con Homebrew):   brew install maven
Instalación en Windows:                descargar Maven de https://maven.apache.org
                                       y agregar la carpeta bin al PATH


3. PASOS DE EJECUCIÓN
-------------------------------------------------------------------------------
Paso 1. Clonar el repositorio
        git clone https://github.com/<usuario>/petstore-api-karate.git

Paso 2. Ingresar a la carpeta del proyecto
        cd petstore-api-karate

Paso 3. Ejecutar las pruebas (elegir UNA opción)

        Opción A - Script (ejecuta y copia los reportes a la carpeta reportes/):
          macOS / Linux:   chmod +x ejecutar.sh  &&  ./ejecutar.sh
          Windows:         ejecutar.bat

          Solo un tag:     ./ejecutar.sh @E2E      |   ejecutar.bat @E2E

        Opción B - Maven directamente:
          mvn clean test
          mvn clean test -Dkarate.options="--tags @E2E"        (solo el flujo)
          mvn clean test -Dkarate.options="--tags @negativo"   (solo el negativo)

        La primera ejecución tarda más porque Maven descarga las dependencias.

Paso 4. Revisar el reporte (abrir en el navegador)
          target/karate-reports/karate-summary.html

        Al hacer clic en el feature se ve cada paso con el request y el response
        completos (entradas y salidas de cada caso). Si se usó el script, el
        reporte queda copiado en reportes/karate-reports/.

        macOS:    open target/karate-reports/karate-summary.html
        Windows:  start target\karate-reports\karate-summary.html


4. ESTRUCTURA DEL PROYECTO
-------------------------------------------------------------------------------
src/test/java/
  karate-config.js                 Configuración global: URL base, timeouts, reintentos
  logback-test.xml                 Configuración de logs
  petstore/
    PetStoreRunner.java            Ejecutor JUnit 5 de los features
    pet/
      pet.feature                  Escenarios de prueba (casos 1 a 4 + negativo)
      data/pet-request.json        Plantilla del body de la mascota (entrada)
      schemas/pet-schema.json      Esquema esperado de la respuesta (validación de contrato)

ejecutar.sh / ejecutar.bat         Scripts de ejecución
reportes/                          Evidencias de la ejecución
conclusiones.txt                   Casos de prueba, hallazgos y conclusiones


5. VARIABLES PRINCIPALES
-------------------------------------------------------------------------------
  baseUrl     URL base de la API (karate-config.js)
  petId       ID único de la mascota, generado en cada ejecución
  petName     Nombre de la mascota (Firulais -> Firulais Actualizado)
  petStatus   Estatus de la mascota (available -> sold)
  petSchema   Esquema con el que se valida cada respuesta
  response    Respuesta de la última petición (variable propia de Karate)


6. SOLUCIÓN DE PROBLEMAS
-------------------------------------------------------------------------------
- "mvn: command not found": Maven no está instalado o no está en el PATH.
- Error descargando karate-junit5: cambiar en el pom.xml el groupId a
  com.intuit.karate y la versión a 1.4.1 (versión anterior de Karate).
- Fallos intermitentes (404 o timeouts): la API es pública y compartida. El
  proyecto ya reintenta las consultas; si persiste, volver a ejecutar.
