package co.com.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutInformationPage {

    public static final Target FIRST_NAME = Target.the("campo nombre").locatedBy("#first-name");
    public static final Target LAST_NAME = Target.the("campo apellido").locatedBy("#last-name");
    public static final Target POSTAL_CODE = Target.the("campo código postal").locatedBy("#postal-code");
    public static final Target CONTINUE_BUTTON = Target.the("botón Continue").locatedBy("#continue");

    private CheckoutInformationPage() {
    }
}
