package petstore;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Ejecuta todos los .feature que hay bajo el paquete petstore.
 * Genera el reporte HTML en target/karate-reports/karate-summary.html
 * y el JSON de Cucumber (útil para integrarlo con otras herramientas de reporte).
 */
class PetStoreRunner {

    @Test
    void ejecutarPruebasPetStore() {
        Results results = Runner.path("classpath:petstore")
                .tags("~@ignore")
                .outputCucumberJson(true)
                .parallel(1);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
