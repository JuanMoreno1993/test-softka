#!/usr/bin/env bash
# Ejecuta la prueba E2E y copia los reportes a la carpeta reportes/ (versionada en el repositorio).
# Uso:
#   ./ejecutar.sh              -> navegador visible
#   ./ejecutar.sh --headless   -> sin interfaz gráfica
set -u

HEADLESS=false
if [ "${1:-}" = "--headless" ]; then
  HEADLESS=true
fi

echo ">> Ejecutando prueba E2E (headless=${HEADLESS})..."
mvn clean verify -Dheadless.mode="${HEADLESS}"
RESULT=$?

echo ">> Copiando reportes a ./reportes ..."
rm -rf reportes/serenity reportes/cucumber
mkdir -p reportes
[ -d target/site/serenity ] && cp -r target/site/serenity reportes/serenity
[ -d target/cucumber-reports ] && cp -r target/cucumber-reports reportes/cucumber

echo ">> Reporte Serenity:        reportes/serenity/index.html"
echo ">> Reporte de una página:   reportes/serenity/serenity-summary.html"
echo ">> Reporte Cucumber:        reportes/cucumber/cucumber.html"
exit $RESULT
