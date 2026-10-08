package co.com.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutCompletePage {

    public static final Target CONFIRMATION_HEADER = Target.the("mensaje de confirmación").locatedBy(".complete-header");

    private CheckoutCompletePage() {
    }
}
