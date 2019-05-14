Feature: Simple check of search and pre-registration (Amazon MX).

  Scenario: Go to Amazon Mexico and almost finish registration
    Given the Amazon url
    When I search for "Samsung Galaxy S9" to store the price
    And click on the first result to compare the price in details and add it to the cart
    And later move to the main page to go to Your Amazon
    And I want to create an account, I have to fill my name "Javier" my email "email" and password "password"
    Then I should validate the prices I saw are the same
    And the page contains the text in spanish "Crear cuenta"