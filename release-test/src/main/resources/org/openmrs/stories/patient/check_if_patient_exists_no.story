Scenario: create patient that does not exist

GivenStories: org/openmrs/stories/patient/navigate_to_find_patient.story

Given I am on Find Patient screen
When I type Hora for name
When I type 73 for age
When I choose Male
When I click Create Person
Then take me to the Create Patient Page