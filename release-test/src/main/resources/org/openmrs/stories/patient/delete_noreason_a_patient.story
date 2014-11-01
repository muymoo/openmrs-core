Scenario: create a patient then delete the patient with no reason i get error

GivenStories: org/openmrs/stories/patient/check_if_patient_exists_no.story

Given I am on the Create Patient Page
When I enter a random name as the name
And I enter 123456 as Identifier Code
And I select Old Identification Number as Identifier Type with index 1
And I click on the button Save
Then take me to Patient dashboard page with title Patient Dashboard
Then the dashboard name should match the last created name

When I edit the patient
When I delete the patient
Then I get an error