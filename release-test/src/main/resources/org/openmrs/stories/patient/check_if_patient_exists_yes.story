Scenario: create patient that already exists

GivenStories: org/openmrs/stories/patient/navigate_to_find_patient.story

Given I am on Find Patient screen
When I type Horatio Hornblower for name
When I type 73 for age
When I choose Male
When I click Create Person
Then I am taken to person screen
Then I see the title Found Similar People
Then I see another similar patient listed as Horatio Hornblower