Scenario: create a patient then delete the patient with no reason i get error

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I edit the patient
When I delete the patient
Then I get an error