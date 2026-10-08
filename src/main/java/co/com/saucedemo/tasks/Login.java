package co.com.saucedemo.tasks;

import co.com.saucedemo.userinterfaces.InventoryPage;
import co.com.saucedemo.userinterfaces.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password) {
        return Tasks.instrumented(Login.class, username, password);
    }

    @Override
    @Step("{0} inicia sesión con el usuario #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(LoginPage.USERNAME),
                Enter.theValue(password).into(LoginPage.PASSWORD),
                Click.on(LoginPage.LOGIN_BUTTON),
                WaitUntil.the(InventoryPage.TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
