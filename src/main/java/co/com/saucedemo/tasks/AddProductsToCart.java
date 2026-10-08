package co.com.saucedemo.tasks;

import co.com.saucedemo.userinterfaces.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;
import java.util.Locale;

public class AddProductsToCart implements Task {

    private final List<String> products;

    public AddProductsToCart(List<String> products) {
        this.products = products;
    }

    public static AddProductsToCart named(List<String> products) {
        return Tasks.instrumented(AddProductsToCart.class, products);
    }

    @Override
    @Step("{0} agrega al carrito los productos #products")
    public <T extends Actor> void performAs(T actor) {
        products.forEach(product ->
                actor.attemptsTo(Click.on(InventoryPage.ADD_TO_CART_BUTTON.of(toProductId(product))))
        );
    }

    /**
     * Sauce Demo identifica cada botón con el nombre del producto en minúsculas y con guiones,
     * p. ej. "Sauce Labs Backpack" -> "add-to-cart-sauce-labs-backpack".
     */
    private static String toProductId(String productName) {
        return productName.trim().toLowerCase(Locale.ROOT).replace(" ", "-");
    }
}
