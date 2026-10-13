===============================================================================
 PRUEBA DE CARGA - SERVICIO DE LOGIN (FakeStoreAPI)
 Herramienta: Grafana k6
===============================================================================

1. DESCRIPCIÓN
-------------------------------------------------------------------------------
Prueba de carga del servicio de autenticación:

  POST https://fakestoreapi.com/auth/login
  Header: Content-Type: application/json
  Body:   { "username": "<user>", "password": "<passwd>" }

Los usuarios y contraseñas se leen del archivo data/usuarios.csv.

Escenario por defecto: 25 transacciones por segundo (TPS) constantes durante
3 minutos (aprox. 4.500 peticiones). Se usan 25 TPS para dejar margen sobre el
mínimo exigido de 20 TPS.

Criterios de aceptación (thresholds de k6):
  - Throughput:            http_reqs        rate >= 20 TPS
  - Tiempo de respuesta:   http_req_duration p(95) < 1500 ms
                           + check por petición: tiempo <= 1500 ms
  - Tasa de error:         http_req_failed  rate < 3 %


2. VERSIONES DE LAS TECNOLOGÍAS
-------------------------------------------------------------------------------
  - Grafana k6      v1.0.0 o superior  (verificar con: k6 version)
                    El script fue validado con k6 v1.0.0.
  - Sistema operativo: macOS 13+, Windows 10/11 o Linux
  - Navegador web (cualquiera) para abrir el reporte dashboard.html
  - Git (para clonar el repositorio)

  No requiere Node.js, Java ni otras dependencias: k6 es un ejecutable único
  y el script usa solo módulos propios de k6 (k6/http, k6/data, k6/metrics).


3. INSTALACIÓN DE K6
-------------------------------------------------------------------------------
  macOS:     brew install k6
  Windows:   winget install k6 --source winget
             (o choco install k6)
  Linux:     ver https://grafana.com/docs/k6/latest/set-up/install-k6/

  Verificar:  k6 version


4. PASOS DE EJECUCIÓN
-------------------------------------------------------------------------------
Paso 1. Clonar el repositorio y entrar a la carpeta del ejercicio
        git clone https://github.com/<usuario>/<repositorio>.git
        cd <repositorio>/performance/ejercicio-1-carga-login-k6

        IMPORTANTE: los comandos se deben ejecutar desde esta carpeta, porque
        el script lee data/usuarios.csv y escribe en reportes/.

Paso 2. Ejecutar la prueba (elegir UNA opción)

        Opción A - Script (genera todos los reportes):
          macOS / Linux:   chmod +x ejecutar.sh  &&  ./ejecutar.sh
          Windows:         ejecutar.bat

          Con otros valores de carga (TPS y duración):
          ./ejecutar.sh 30 5m       |     ejecutar.bat 30 5m

        Opción B - k6 directamente:
          k6 run scripts/login-load-test.js
          k6 run -e TPS=30 -e DURACION=5m scripts/login-load-test.js

Paso 3. Revisar los resultados
        - En consola: al final se imprime el resumen con CUMPLE / NO CUMPLE
          para cada criterio.
        - reportes/dashboard.html   gráficas de la ejecución (abrir en el navegador)
        - reportes/resumen.txt      resumen del resultado
        - reportes/resumen.json     todas las métricas
        - reportes/resultados.csv.gz   detalle de cada petición

        Si algún criterio no se cumple, k6 termina con código de salida 99.


5. ESTRUCTURA
-------------------------------------------------------------------------------
  scripts/login-load-test.js   Script de la prueba (escenario, thresholds, checks)
  data/usuarios.csv            Datos de entrada parametrizados (user,passwd)
  ejecutar.sh / ejecutar.bat   Scripts de ejecución
  reportes/                    Evidencias de la ejecución
  conclusiones.txt             Hallazgos y conclusiones


6. PARÁMETROS DEL SCRIPT
-------------------------------------------------------------------------------
  TPS        Transacciones por segundo objetivo          (por defecto 25)
  DURACION   Duración de la carga sostenida              (por defecto 3m)
  BASE_URL   URL del servicio                            (por defecto https://fakestoreapi.com)

  Ejemplo: k6 run -e TPS=40 -e DURACION=10m scripts/login-load-test.js
