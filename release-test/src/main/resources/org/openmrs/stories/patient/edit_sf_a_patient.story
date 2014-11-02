Scenario: then edit the patient in the short form and verify saves

GivenStories: org/openmrs/stories/patient/create_a_complex_patient.story

When I edit the patient in the short form
When I enter 278 West Bloomfield as address
And I click on the button Save
Then the information should be saved 
Then address1 should match 278 West Bloomfield