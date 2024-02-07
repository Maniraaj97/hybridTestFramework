
@tag
Feature: Purchase Order from Ecommenrce Site
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	
  @Positive
  Scenario: Poisitve Test of Submit Order
    Given Logged in with username <name> and pwd <pwd> 
    When I add product <productName> to Cart
    And Checkout <productName> and submit the Order at <countryName>
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    
  Examples: 
      | name  							 |	 pwd | 		productName | countryName |
      | maniraajsn@gmail.com | M@n1raaj | ZARA COAT 3 | Canada |
       
