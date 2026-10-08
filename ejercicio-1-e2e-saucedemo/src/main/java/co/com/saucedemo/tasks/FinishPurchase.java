package co.com.saucedemo.tasks;

import co.com.saucedemo.userinterfaces.CheckoutCompletePage;
import co.com.saucedemo.userinterfaces.CheckoutOverviewPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FinishPurchase implements Task {

    public static FinishPurchase now() {
        return Tasks.instrumented(FinishPurchase.class);
    }

    @Override
    @Step("{0} finaliza la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(CheckoutOverviewPage.FINISH_BUTTON),
                WaitUntil.the(CheckoutCompletePage.CONFIRMATION_HEADER, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
