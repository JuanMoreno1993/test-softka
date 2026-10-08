package co.com.saucedemo.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    /** Prepara el escenario: cada actor recibe automáticamente la habilidad de navegar la web. */
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
