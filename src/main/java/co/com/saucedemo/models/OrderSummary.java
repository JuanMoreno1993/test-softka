package co.com.saucedemo.models;

import java.math.BigDecimal;

/**
 * Valores del resumen de la orden (pantalla Checkout: Overview).
 *
 * @param sumOfItemPrices suma de los precios individuales de los productos listados
 * @param itemTotal       valor mostrado como "Item total"
 * @param tax             valor mostrado como "Tax"
 * @param total           valor mostrado como "Total"
 */
public record OrderSummary(BigDecimal sumOfItemPrices, BigDecimal itemTotal, BigDecimal tax, BigDecimal total) {
}
