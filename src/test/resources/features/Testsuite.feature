Feature: UI-Test for Web-App using Playwright

  @Login
  Scenario Outline: Test Login Process
    Given Navigate to application
    When Login to application with"<Username>" and "<Password>"
    Then Verify Login
    Examples:
      | Username      | Password     |
      | standard_user | secret_sauce |

  @Order
  Scenario Outline: Test Order Process
    Given Add first "<Product1>" to Shopping Cart
    And Add second "<Product2>" to Shopping Cart
    And Navigate to Shopping Cart
    Then Verify first "<Product1>" in Shopping Cart
    Then Verify second "<Product2>" in Shopping Cart
    Examples:
      | Product1              | Product2            |
      | Sauce Labs Bike Light | Sauce Labs Backpack |

  @Checkout
  Scenario Outline: Test Checkout Process
    Given Navigate to Checkout
    And Add User Information "<firstName>" and "<lastname>" and "<PLZ>"
    And Validate Checkout Overview "<Product1>" and "<Product2>"
    And Finish Order
    Then Verify Finish Order
    Examples:
      | firstName | lastname | PLZ   | Product1              | Product2            |
      | Demo      | Test     | 22222 | Sauce Labs Bike Light | Sauce Labs Backpack |
