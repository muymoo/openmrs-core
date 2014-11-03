Scenario: add an allergy with no allergy

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I click dashboard Add Allergy
When I click the Save button
Then the allergy error is displayed

Scenario: add a allergy click cancel

When I click the Cancel button
Then the dashboard name should match the last created name

Scenario: add a allergy and dashboard displayed

When I click dashboard Add Allergy
When I enter an allergen ABACAVIR
When I click the Cancel button
Then the dashboard name should match the last created name