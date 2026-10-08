#!/usr/bin/env bash
# Ejecuta las pruebas de API y copia los reportes a la carpeta reportes/ (versionada en el repositorio).
# Uso:
#   ./ejecutar.sh                 -> todos los escenarios
#   ./ejecutar.sh @E2E            -> solo los escenarios con ese tag
set -u

if [ -n "${1:-}" ]; then
  echo ">> Ejecutando escenarios con el tag $1 ..."
  mvn clean test -Dkarate.options="--tags $1"
else
  echo ">> Ejecutando todas las pruebas de API..."
  mvn clean test
fi
RESULT=$?

echo ">> Copiando reportes a ./reportes ..."
rm -rf reportes/karate-reports
mkdir -p reportes
[ -d target/karate-reports ] && cp -r target/karate-reports reportes/karate-reports
[ -f target/karate.log ] && cp target/karate.log reportes/karate.log

echo ">> Reporte Karate:  reportes/karate-reports/karate-summary.html"
echo ">> Log completo:    reportes/karate.log"
exit $RESULT
