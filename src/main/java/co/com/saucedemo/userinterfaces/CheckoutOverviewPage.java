package co.com.saucedemo.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutOverviewPage {

    public static final Target ITEM_PRICES = Target.the("precios de los productos").locatedBy(".cart_item .inventory_item_price");
    public static final Target SUBTOTAL = Target.the("subtotal").locatedBy(".summary_subtotal_label");
    public static final Target TAX = Target.the("impuesto").locatedBy(".summary_tax_label");
    public static final Target TOTAL = Target.the("total").locatedBy(".summary_total_label");
    public static final Target FINISH_BUTTON = Target.the("botón Finish").locatedBy("#finish");

    private CheckoutOverviewPage() {
    }
}
