Scenario: then edit the patient in short form, Back, it should not save

GivenStories: org/openmrs/stories/patient/create_a_complex_patient.story

When I edit the patient in the short form
When I enter 278 West Bloomfield as address
And I click back
Then address1 should match 123 West Frankfurt