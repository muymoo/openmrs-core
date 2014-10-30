Scenario: click tab to get to Find/Create Patient tab

GivenStories: org/openmrs/stories/login/successful_login.story

Given I am on Home screen
When I click on the Find/Create Patient link
Then take me to Find/Create Patient Page with Find Patient as title


Scenario: search for an existing patient

Given I am on Find Patient screen
When I type hora into the search box
Then Horatio Hornblower is returned


Scenario: search for a patient that doesn't exist

Given I am on Find Patient screen
When I type foo into the search box
Then no patients are returned