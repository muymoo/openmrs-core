Scenario: add a problem with no problem

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I click dashboard Add Problem
When I click the problem Save button
Then the problem error is displayed

Scenario: add a problem click cancel

When I click the problem Cancel button
Then the dashboard name should match the last created name

Scenario: add a problem and dashboard displayed

When I click dashboard Add Problem
When I enter a problem GALLSTONES
When I click the problem Save button
Then the dashboard name should match the last created name