Scenario: create a patient

GivenStories: org/openmrs/stories/patient/check_if_patient_exists_no.story

Given I am on the Create Patient Page
When I enter a random name as the name
And I enter 123456 as Identifier Code
And I select Old Identification Number as Identifier Type with index 1
And I enter 2 Jones Lane as address
And I click on the button Save
Then take me to Patient dashboard page with title Patient Dashboard
Then the dashboard name should match the last created name
Then address1 should match 2 Jones Lane

Scenario: then edit the patient in the short form and verify saves

When I edit the patient in the short form
When I enter 278 West Bloomfield as address
And I click on the button Save
Then the information should be saved 
Then address1 should match 278 West Bloomfield