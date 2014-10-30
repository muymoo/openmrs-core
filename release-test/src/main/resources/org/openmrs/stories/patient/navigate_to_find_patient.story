Scenario: click tab to get to Find/Create Patient tab

GivenStories: org/openmrs/stories/login/successful_login.story

Given I am on Home screen
When I click on the Find/Create Patient link
Then take me to Find/Create Patient Page with Find Patient as title