# Sauce Demo – Prueba E2E del flujo de compra

Prueba funcional automatizada (E2E) del flujo de compra en [saucedemo.com](https://www.saucedemo.com/) con **Serenity BDD + Screenplay + Cucumber** (Java 17, Maven).

```gherkin
Escenario: Compra exitosa de dos productos hasta la confirmación del pedido
  Cuando se autentica con el usuario "standard_user" y la contraseña "secret_sauce"
  Y agrega los siguientes productos al carrito
  Y visualiza el carrito de compras
  Entonces debería ver en el carrito los productos seleccionados
  Cuando completa el formulario de compra con sus datos
  Entonces el resumen de la orden debería tener los totales correctos
  Cuando finaliza la compra
  Entonces debería ver el mensaje de confirmación "THANK YOU FOR YOUR ORDER"
```

## Ejecución rápida

```bash
mvn clean verify                          # con navegador visible
mvn clean verify -Dheadless.mode=true     # sin interfaz gráfica
```

Reporte: `target/site/serenity/index.html`

- Instrucciones paso a paso: [`readme.txt`](readme.txt)
- Hallazgos y conclusiones: [`conclusiones.txt`](conclusiones.txt)
- Evidencias de ejecución: [`reportes/`](reportes/)
