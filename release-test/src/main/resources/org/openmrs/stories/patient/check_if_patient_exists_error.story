Scenario: create patient with no information

GivenStories: org/openmrs/stories/patient/navigate_to_find_patient.story

Given I am on Find Patient screen
When I click Create Person
Then Person Name has an error
Then Age has an error
Then Gender has an error

Given I am on Find Patient screen
When I type 12345 for name
When I type 73 for age
When I choose Male
When I click Create Person
Then Person Name has an invalid error

Given I am on Find Patient screen
When I type abc for name
When I type fg for age
When I choose Male
When I click Create Person
Then Age has an error