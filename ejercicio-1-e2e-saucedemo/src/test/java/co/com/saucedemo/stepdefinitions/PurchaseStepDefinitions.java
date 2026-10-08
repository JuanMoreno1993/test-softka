package co.com.saucedemo.stepdefinitions;

import co.com.saucedemo.models.Buyer;
import co.com.saucedemo.models.OrderSummary;
import co.com.saucedemo.questions.CartBadge;
import co.com.saucedemo.questions.ConfirmationMessage;
import co.com.saucedemo.questions.ProductsInCart;
import co.com.saucedemo.questions.TheOrderSummary;
import co.com.saucedemo.tasks.AddProductsToCart;
import co.com.saucedemo.tasks.CompleteCheckoutInformation;
import co.com.saucedemo.tasks.FinishPurchase;
import co.com.saucedemo.tasks.GoToCart;
import co.com.saucedemo.tasks.Login;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actions.Open;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class PurchaseStepDefinitions {

    private static final String SAUCE_DEMO_URL = "https://www.saucedemo.com/";

    /** Productos agregados en el escenario, para compararlos luego con el carrito. */
    private final List<String> selectedProducts = new ArrayList<>();

    @Dado("que {string} abre la tienda Sauce Demo")
    public void openTheStore(String actorName) {
        theActorCalled(actorName).wasAbleTo(Open.url(SAUCE_DEMO_URL));
    }

    @Cuando("se autentica con el usuario {string} y la contraseña {string}")
    public void logIn(String username, String password) {
        theActorInTheSpotlight().attemptsTo(Login.withCredentials(username, password));
    }

    @Cuando("agrega los siguientes productos al carrito")
    public void addProducts(List<String> products) {
        selectedProducts.addAll(products);
        theActorInTheSpotlight().attemptsTo(AddProductsToCart.named(products));
        theActorInTheSpotlight().should(
                seeThat("el contador del carrito", CartBadge.count(), equalTo(products.size()))
        );
    }

    @Cuando("visualiza el carrito de compras")
    public void viewCart() {
        theActorInTheSpotlight().attemptsTo(GoToCart.now());
    }

    @Entonces("debería ver en el carrito los productos seleccionados")
    public void shouldSeeSelectedProductsInCart() {
        theActorInTheSpotlight().should(
                seeThat("la cantidad de productos del carrito", ProductsInCart.displayed(), hasSize(selectedProducts.size())),
                seeThat("los productos del carrito", ProductsInCart.displayed(),
                        containsInAnyOrder(selectedProducts.toArray(new String[0])))
        );
    }

    @Cuando("completa el formulario de compra con sus datos")
    public void completeCheckoutForm(List<Map<String, String>> data) {
        theActorInTheSpotlight().attemptsTo(CompleteCheckoutInformation.with(Buyer.from(data.get(0))));
    }

    @Entonces("el resumen de la orden debería tener los totales correctos")
    public void orderSummaryShouldBeConsistent() {
        OrderSummary summary = theActorInTheSpotlight().asksFor(TheOrderSummary.displayed());

        assertThat("El Item total debe ser la suma de los precios de los productos",
                summary.itemTotal(), comparesEqualTo(summary.sumOfItemPrices()));
        assertThat("El Total debe ser Item total + Tax",
                summary.total(), comparesEqualTo(summary.itemTotal().add(summary.tax())));
    }

    @Cuando("finaliza la compra")
    public void finishPurchase() {
        theActorInTheSpotlight().attemptsTo(FinishPurchase.now());
    }

    @Entonces("debería ver el mensaje de confirmación {string}")
    public void shouldSeeConfirmation(String expectedMessage) {
        // La página muestra "Thank you for your order!": se compara sin distinguir mayúsculas
        theActorInTheSpotlight().should(
                seeThat("el mensaje de confirmación", ConfirmationMessage.displayed(),
                        containsStringIgnoringCase(expectedMessage))
        );
    }
}
