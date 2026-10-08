package co.com.saucedemo.questions;

import co.com.saucedemo.userinterfaces.InventoryPage;
import net.serenitybdd.screenplay.Question;

public class CartBadge {

    private CartBadge() {
    }

    /** Número que muestra el ícono del carrito. */
    public static Question<Integer> count() {
        return Question.about("el contador del carrito").answeredBy(actor ->
                Integer.parseInt(InventoryPage.CART_BADGE.resolveFor(actor).getText().trim())
        );
    }
}
