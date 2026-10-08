package co.com.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    public static final Target TITLE = Target.the("título de la página").locatedBy(".title");

    /** Botón "Add to cart" de un producto. {0} = id del producto, p. ej. sauce-labs-backpack */
    public static final Target ADD_TO_CART_BUTTON = Target.the("botón agregar {0} al carrito")
            .locatedBy("#add-to-cart-{0}");

    public static final Target CART_LINK = Target.the("ícono del carrito").locatedBy(".shopping_cart_link");
    public static final Target CART_BADGE = Target.the("contador del carrito").locatedBy(".shopping_cart_badge");

    private InventoryPage() {
    }
}
