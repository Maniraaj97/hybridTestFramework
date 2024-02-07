@tag
Feature: Error Validation
  I want to use this template for my feature file

  @Negative
  Scenario Outline: Failed scenarios
    Given I landed on Ecommerce Page
    When Logged in with username <name> and pwd <pwd>
    Then "Incorrect email or password." message is displayed

 Examples: 
      | name  							 |	 pwd | 		
      | maniraajsn@gmail.com | M@n1raa1 |
