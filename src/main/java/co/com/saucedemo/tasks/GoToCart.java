package co.com.saucedemo.tasks;

import co.com.saucedemo.userinterfaces.CartPage;
import co.com.saucedemo.userinterfaces.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCart implements Task {

    public static GoToCart now() {
        return Tasks.instrumented(GoToCart.class);
    }

    @Override
    @Step("{0} abre el carrito de compras")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(InventoryPage.CART_LINK),
                WaitUntil.the(CartPage.CHECKOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
