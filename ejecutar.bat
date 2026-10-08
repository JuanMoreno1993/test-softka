@echo off
REM Ejecuta la prueba E2E y copia los reportes a la carpeta reportes\ (versionada en el repositorio).
REM Uso:
REM   ejecutar.bat              -> navegador visible
REM   ejecutar.bat --headless   -> sin interfaz grafica

set HEADLESS=false
if "%1"=="--headless" set HEADLESS=true

echo ^>^> Ejecutando prueba E2E (headless=%HEADLESS%)...
call mvn clean verify -Dheadless.mode=%HEADLESS%
set RESULT=%ERRORLEVEL%

echo ^>^> Copiando reportes a .\reportes ...
if exist reportes\serenity rmdir /s /q reportes\serenity
if exist reportes\cucumber rmdir /s /q reportes\cucumber
if not exist reportes mkdir reportes
if exist target\site\serenity xcopy /e /i /q /y target\site\serenity reportes\serenity >nul
if exist target\cucumber-reports xcopy /e /i /q /y target\cucumber-reports reportes\cucumber >nul

echo ^>^> Reporte Serenity:        reportes\serenity\index.html
echo ^>^> Reporte de una pagina:   reportes\serenity\serenity-summary.html
echo ^>^> Reporte Cucumber:        reportes\cucumber\cucumber.html
exit /b %RESULT%
