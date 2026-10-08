package co.com.saucedemo.questions;

import co.com.saucedemo.models.OrderSummary;
import co.com.saucedemo.userinterfaces.CheckoutOverviewPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TheOrderSummary {

    private static final Pattern AMOUNT = Pattern.compile("(\\d+(?:\\.\\d+)?)");

    private TheOrderSummary() {
    }

    /** Lee los montos de la pantalla Checkout: Overview. */
    public static Question<OrderSummary> displayed() {
        return Question.about("el resumen de la orden").answeredBy(actor -> {
            BigDecimal sumOfPrices = CheckoutOverviewPage.ITEM_PRICES.resolveAllFor(actor).stream()
                    .map(WebElementFacade::getText)
                    .map(TheOrderSummary::toAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return new OrderSummary(
                    sumOfPrices,
                    toAmount(CheckoutOverviewPage.SUBTOTAL.resolveFor(actor).getText()),
                    toAmount(CheckoutOverviewPage.TAX.resolveFor(actor).getText()),
                    toAmount(CheckoutOverviewPage.TOTAL.resolveFor(actor).getText())
            );
        });
    }

    /** Extrae el valor numérico de textos como "$29.99" o "Item total: $39.98". */
    static BigDecimal toAmount(String text) {
        Matcher matcher = AMOUNT.matcher(text.replace(",", ""));
        if (!matcher.find()) {
            throw new IllegalStateException("No se encontró un monto en el texto: " + text);
        }
        return new BigDecimal(matcher.group(1));
    }
}
