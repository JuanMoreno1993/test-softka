@petstore
Feature: Gestión de mascotas en la API PetStore
  Como usuario de la API PetStore
  Quiero registrar, consultar y actualizar mascotas
  Para mantener actualizado el inventario de la tienda

  Background:
    * url baseUrl
    # ID único por ejecución (milisegundos actuales) para no chocar con datos de otros usuarios
    * def petId = Java.type('java.lang.System').currentTimeMillis()
    * def petSchema = read('classpath:petstore/pet/schemas/pet-schema.json')

  @E2E
  Scenario: Flujo completo - añadir, consultar por ID, actualizar y consultar por estatus

    # ======================================================================
    # CASO 1: AÑADIR UNA MASCOTA A LA TIENDA            POST /pet
    # ======================================================================
    * def petName = 'Firulais'
    * def petStatus = 'available'
    * def createRequest = read('classpath:petstore/pet/data/pet-request.json')
    * print 'CASO 1 - Entrada (request):', createRequest

    Given path 'pet'
    And request createRequest
    When method post
    Then status 200
    And match response == petSchema
    And match response contains { id: '#(petId)', name: 'Firulais', status: 'available' }
    * print 'CASO 1 - Salida (response):', response

    # ======================================================================
    # CASO 2: CONSULTAR LA MASCOTA POR ID               GET /pet/{petId}
    # ======================================================================
    * print 'CASO 2 - Entrada: petId =', petId

    Given path 'pet', petId
    # La API pública a veces responde 404 justo después de crear: se reintenta
    And retry until responseStatus == 200
    When method get
    Then status 200
    And match response == petSchema
    And match response.id == petId
    And match response.name == 'Firulais'
    And match response.status == 'available'
    * print 'CASO 2 - Salida (response):', response

    # ======================================================================
    # CASO 3: ACTUALIZAR NOMBRE Y ESTATUS A "sold"      PUT /pet
    # ======================================================================
    * def petName = 'Firulais Actualizado'
    * def petStatus = 'sold'
    * def updateRequest = read('classpath:petstore/pet/data/pet-request.json')
    * print 'CASO 3 - Entrada (request):', updateRequest

    Given path 'pet'
    And request updateRequest
    When method put
    Then status 200
    And match response == petSchema
    And match response contains { id: '#(petId)', name: 'Firulais Actualizado', status: 'sold' }
    * print 'CASO 3 - Salida (response):', response

    # ======================================================================
    # CASO 4: CONSULTAR LA MASCOTA POR ESTATUS          GET /pet/findByStatus?status=sold
    # ======================================================================
    * def isMyPet = function(pet){ return pet.id == petId }
    * print 'CASO 4 - Entrada: status = sold'

    Given path 'pet', 'findByStatus'
    And param status = 'sold'
    # Se reintenta hasta que la mascota actualizada aparezca en la lista
    And retry until responseStatus == 200 && karate.filter(response, isMyPet).length == 1
    When method get
    Then status 200
    And match response == '#[_ > 0]'
    And match each response contains { status: 'sold' }

    * def myPet = karate.filter(response, isMyPet)[0]
    And match myPet == petSchema
    And match myPet contains { id: '#(petId)', name: 'Firulais Actualizado', status: 'sold' }
    * print 'CASO 4 - Salida: total de mascotas con estatus sold =', response.length
    * print 'CASO 4 - Salida: mascota encontrada =', myPet

  @negativo
  Scenario: Consultar una mascota que no existe retorna 404
    # El petId generado en el Background nunca se crea en este escenario
    Given path 'pet', petId
    When method get
    Then status 404
    And match response.message == 'Pet not found'
