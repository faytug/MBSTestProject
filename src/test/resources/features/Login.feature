@Login
Feature: Login Functionality
  As a user, I want to log into the application so that I can access my dashboard.


  Background:
    Given user goes to Swagger page
    When user goes to login page

  @Smoke @Positive
  Scenario: Successful login via Swagger redirect
    And the user enters valid username
    And the user enters valid password
    And user clicks on SignIn button
    Then user should be redirected to dashboard page

  @Smoke @Negative
  Scenario: Unsuccessful login with invalid credentials via Swagger redirect
    And the user enters invalid username
    And the user enters invalid password
    And user clicks on SignIn button
    Then An error message should be displayed
    And User should not be redirected to the dashboard