package co.com.saucedemo.questions;

import co.com.saucedemo.userinterfaces.CartPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ProductsInCart {

    private ProductsInCart() {
    }

    /** Nombres de los productos listados en el carrito. */
    public static Question<List<String>> displayed() {
        return Question.about("los productos del carrito").answeredBy(actor ->
                CartPage.ITEM_NAMES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .map(String::trim)
                        .toList()
        );
    }
}
