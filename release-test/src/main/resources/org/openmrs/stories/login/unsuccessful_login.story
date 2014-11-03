Scenario: wrong username does not login to website

Given I am on the login page of OpenMRS
When I enter a bad username
When I enter a valid password
When I click the login button
Then I am not logged in
Then I get the incorrect username/password error message


Scenario: wrong password does not login to website

Given I am on the login page of OpenMRS
When I enter a valid username
When I enter a bad password
When I click the login button
Then I am not logged in
Then I get the incorrect username/password error message