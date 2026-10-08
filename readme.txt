===============================================================================
 PRUEBA E2E AUTOMATIZADA - FLUJO DE COMPRA EN SAUCE DEMO
 Serenity BDD + Screenplay + Cucumber + Selenium WebDriver (Java)
===============================================================================

1. DESCRIPCIÓN
-------------------------------------------------------------------------------
Prueba funcional automatizada de extremo a extremo (E2E) sobre
https://www.saucedemo.com/ que cubre el flujo de compra completo:

  1. Autenticarse con el usuario standard_user / secret_sauce
  2. Agregar dos productos al carrito (Sauce Labs Backpack y Sauce Labs Bike Light)
  3. Visualizar el carrito y validar que contiene los productos agregados
  4. Completar el formulario de compra (nombre, apellido, código postal)
  5. Validar los totales del resumen de la orden
  6. Finalizar la compra y validar el mensaje "THANK YOU FOR YOUR ORDER"


2. PRERREQUISITOS
-------------------------------------------------------------------------------
  - Java JDK 17 o superior        Verificar con:  java -version
  - Apache Maven 3.8 o superior   Verificar con:  mvn -v
  - Google Chrome (última versión)
  - Git
  - Conexión a internet (para descargar dependencias y acceder a saucedemo.com)

No es necesario descargar el ChromeDriver: Selenium Manager descarga
automáticamente el driver compatible con la versión de Chrome instalada.


3. PASOS DE EJECUCIÓN
-------------------------------------------------------------------------------
Paso 1. Clonar el repositorio
        git clone https://github.com/<usuario>/saucedemo-e2e-serenity.git

Paso 2. Ingresar a la carpeta del proyecto
        cd saucedemo-e2e-serenity

Paso 3. Ejecutar la prueba (elegir UNA opción)

        Opción A - Script (ejecuta y copia los reportes a la carpeta reportes/):
          Windows:          ejecutar.bat
          Linux / macOS:    chmod +x ejecutar.sh  &&  ./ejecutar.sh

          Para ejecutar sin abrir el navegador agregar --headless:
          ejecutar.bat --headless      |      ./ejecutar.sh --headless

        Opción B - Maven directamente:
          mvn clean verify
          mvn clean verify -Dheadless.mode=true             (sin interfaz gráfica)
          mvn clean verify -Dcucumber.filter.tags="@E2E"    (filtrar por tag)

        La primera ejecución tarda más porque Maven descarga las dependencias.

Paso 4. Revisar los reportes (abrir en cualquier navegador)
          target/site/serenity/index.html              Reporte completo de Serenity BDD
          target/site/serenity/serenity-summary.html   Reporte resumido de una página
          target/cucumber-reports/cucumber.html        Reporte de Cucumber

        Si se usó el script (Opción A), los mismos reportes quedan copiados en
        la carpeta reportes/ del proyecto.


4. ESTRUCTURA DEL PROYECTO (patrón Screenplay)
-------------------------------------------------------------------------------
src/main/java/co/com/saucedemo/
  userinterfaces/   Localizadores (Targets) de cada pantalla
                    LoginPage, InventoryPage, CartPage, CheckoutInformationPage,
                    CheckoutOverviewPage, CheckoutCompletePage
  tasks/            Acciones de negocio del actor
                    Login, AddProductsToCart, GoToCart,
                    CompleteCheckoutInformation, FinishPurchase
  questions/        Consultas sobre el estado de la aplicación para validar
                    CartBadge, ProductsInCart, TheOrderSummary, ConfirmationMessage
  models/           Objetos de datos: Buyer, OrderSummary

src/test/java/co/com/saucedemo/
  runners/          CucumberTestSuite (ejecutor de JUnit 5 + Cucumber)
  stepdefinitions/  PurchaseStepDefinitions (pasos del feature) y Hooks

src/test/resources/
  features/compra.feature     Escenario en Gherkin (español)
  serenity.conf               Configuración de Serenity y del navegador
  junit-platform.properties   Configuración de Cucumber

ejecutar.sh / ejecutar.bat    Scripts de ejecución
.github/workflows/e2e.yml     Ejecución automática en GitHub Actions
reportes/                     Evidencias de la ejecución
conclusiones.txt              Hallazgos y conclusiones del ejercicio


5. EJECUCIÓN EN GITHUB ACTIONS (OPCIONAL)
-------------------------------------------------------------------------------
Cada push a la rama main ejecuta la prueba en modo headless. El reporte de
Serenity se puede descargar desde la pestaña "Actions" > ejecución >
"Artifacts" > reporte-serenity.


6. SOLUCIÓN DE PROBLEMAS
-------------------------------------------------------------------------------
- "SessionNotCreatedException" o error de versión de driver:
  actualizar Google Chrome a la última versión y volver a ejecutar.
- Falla al descargar dependencias: verificar conexión a internet o proxy
  corporativo (configurar en ~/.m2/settings.xml).
- Aparece un popup de Chrome sobre "contraseña filtrada": ya está desactivado
  en serenity.conf (modo incógnito + preferencias del gestor de contraseñas).
