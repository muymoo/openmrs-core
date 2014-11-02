Scenario: Create a patient and verify basic info is saved

GivenStories: org/openmrs/stories/patient/check_if_patient_exists_no.story

Given I am on the Create Patient Page
When I enter a random name as the name
And I enter a random code as Identifier Code
And I select Old Identification Number as Identifier Type with index 1
And I select Unknown Location as location with index 1
And I select preferred option
And I enter 123 West Frankfurt as address
And I enter India as country
And I click on the button Save
Then take me to Patient dashboard page with title Patient Dashboard
Then the dashboard name should match the last created name
Then address1 should match 123 West Frankfurt
Then age should match 37