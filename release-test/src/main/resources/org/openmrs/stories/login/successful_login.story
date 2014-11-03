Scenario: username/password from system properties successful

Given I am on the login page of OpenMRS
When I enter a valid username
When I enter a valid password
When I click the login button
Then take me to the Home screen and display welcome message for user Super