package co.com.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final Target TITLE = Target.the("título del carrito").locatedBy(".title");
    public static final Target ITEM_NAMES = Target.the("nombres de los productos del carrito")
            .locatedBy(".cart_item .inventory_item_name");
    public static final Target CHECKOUT_BUTTON = Target.the("botón Checkout").locatedBy("#checkout");

    private CartPage() {
    }
}
