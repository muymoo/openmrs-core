Scenario: search for a patient that doesn't exist

GivenStories: org/openmrs/stories/patient/navigate_to_find_patient.story

Given I am on Find Patient screen
When I type foo into the search box
Then no patients are returned
