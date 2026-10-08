package co.com.saucedemo.tasks;

import co.com.saucedemo.models.Buyer;
import co.com.saucedemo.userinterfaces.CartPage;
import co.com.saucedemo.userinterfaces.CheckoutInformationPage;
import co.com.saucedemo.userinterfaces.CheckoutOverviewPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompleteCheckoutInformation implements Task {

    private final Buyer buyer;

    public CompleteCheckoutInformation(Buyer buyer) {
        this.buyer = buyer;
    }

    public static CompleteCheckoutInformation with(Buyer buyer) {
        return Tasks.instrumented(CompleteCheckoutInformation.class, buyer);
    }

    @Override
    @Step("{0} diligencia el formulario de compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CartPage.CHECKOUT_BUTTON),
                Enter.theValue(buyer.firstName()).into(CheckoutInformationPage.FIRST_NAME),
                Enter.theValue(buyer.lastName()).into(CheckoutInformationPage.LAST_NAME),
                Enter.theValue(buyer.postalCode()).into(CheckoutInformationPage.POSTAL_CODE),
                Click.on(CheckoutInformationPage.CONTINUE_BUTTON),
                WaitUntil.the(CheckoutOverviewPage.FINISH_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
