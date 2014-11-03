Scenario: add a program with no program

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I click dashboard Add a new program
When I click the program Enroll button
Then an alert is displayed and I hit ok

Scenario: add a program click cancel

When I click the program Cancel button
Then the dashboard name should match the last created name
Then the patient is in no programs

Scenario: add a program with type

When I click dashboard Add a new program
When I choose HIV program
When I click the program Enroll button
Then the enrollment date missing error is displayed

Scenario: add a program with type and enrollment date

When I click dashboard Add a new program
When I choose HIV program
When I set the enrollment date to 11/01/2014
When I click the program Enroll button
Then the program is displayed in the table

Scenario: click on program and see more info

When I click on the HIV program
Then the edit program popup comes up
Then it says the full name of the program