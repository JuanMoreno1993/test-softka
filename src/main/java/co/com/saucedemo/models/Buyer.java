package co.com.saucedemo.models;

import java.util.Map;

/** Datos del comprador que se diligencian en el formulario de checkout. */
public record Buyer(String firstName, String lastName, String postalCode) {

    public static Buyer from(Map<String, String> row) {
        return new Buyer(row.get("nombre"), row.get("apellido"), row.get("codigoPostal"));
    }
}
