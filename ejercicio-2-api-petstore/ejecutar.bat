@echo off
REM Ejecuta las pruebas de API y copia los reportes a la carpeta reportes\ (versionada en el repositorio).
REM Uso:
REM   ejecutar.bat          -> todos los escenarios
REM   ejecutar.bat @E2E     -> solo los escenarios con ese tag

if "%1"=="" (
  echo ^>^> Ejecutando todas las pruebas de API...
  call mvn clean test
) else (
  echo ^>^> Ejecutando escenarios con el tag %1 ...
  call mvn clean test "-Dkarate.options=--tags %1"
)
set RESULT=%ERRORLEVEL%

echo ^>^> Copiando reportes a .\reportes ...
if exist reportes\karate-reports rmdir /s /q reportes\karate-reports
if not exist reportes mkdir reportes
if exist target\karate-reports xcopy /e /i /q /y target\karate-reports reportes\karate-reports >nul
if exist target\karate.log copy /y target\karate.log reportes\karate.log >nul

echo ^>^> Reporte Karate:  reportes\karate-reports\karate-summary.html
echo ^>^> Log completo:    reportes\karate.log
exit /b %RESULT%
