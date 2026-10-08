# language: es
Característica: Flujo de compra en Sauce Demo
  Como cliente de la tienda Sauce Demo
  Quiero comprar productos agregándolos al carrito
  Para recibir mi pedido

  Antecedentes:
    Dado que "Juan" abre la tienda Sauce Demo

  @E2E @CompraExitosa
  Escenario: Compra exitosa de dos productos hasta la confirmación del pedido
    Cuando se autentica con el usuario "standard_user" y la contraseña "secret_sauce"
    Y agrega los siguientes productos al carrito
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    Y visualiza el carrito de compras
    Entonces debería ver en el carrito los productos seleccionados
    Cuando completa el formulario de compra con sus datos
      | nombre | apellido | codigoPostal |
      | Juan   | Moreno   | 110111       |
    Entonces el resumen de la orden debería tener los totales correctos
    Cuando finaliza la compra
    Entonces debería ver el mensaje de confirmación "THANK YOU FOR YOUR ORDER"
