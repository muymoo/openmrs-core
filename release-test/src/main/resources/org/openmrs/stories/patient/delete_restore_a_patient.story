Scenario: create a patient then delete the patient with reason they are deleted

GivenStories: org/openmrs/stories/patient/check_if_patient_exists_no.story

Given I am on the Create Patient Page
When I enter a random name as the name
And I enter 123456 as Identifier Code
And I select Old Identification Number as Identifier Type with index 1
And I click on the button Save
Then take me to Patient dashboard page with title Patient Dashboard
Then the dashboard name should match the last created name

When I edit the patient
When I type in a reason
When I delete the patient
Then the message says Patient deleted

When I click Restore Patient
Then the message says Patient restored

When I click on the Find/Create Patient link
When I search for the last created name
Then what is returned is the last created name