package co.com.saucedemo.questions;

import co.com.saucedemo.userinterfaces.CheckoutCompletePage;
import net.serenitybdd.screenplay.Question;

public class ConfirmationMessage {

    private ConfirmationMessage() {
    }

    /** Texto del encabezado de la pantalla Checkout: Complete. */
    public static Question<String> displayed() {
        return Question.about("el mensaje de confirmación").answeredBy(actor ->
                CheckoutCompletePage.CONFIRMATION_HEADER.resolveFor(actor).getText().trim()
        );
    }
}
