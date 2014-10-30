Scenario: Create a patient

GivenStories: org/openmrs/stories/patient/check_if_patient_exists_no.story

Given I am on the Create Patient Page
When I enter the Clive as the family name
And I enter 123456 as Identifier Code
And I select Old Identification Number as Identifier Type with index 1
And I select Unknown Location as location with index 1
And I select preferred option
And I enter address as address
And I enter India as country
And I click on the button Save
Then take me to Patient dashboard page with title Patient Dashboard




