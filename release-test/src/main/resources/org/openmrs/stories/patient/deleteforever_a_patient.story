Scenario: create a patient then delete the patient with reason they are deleted

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I edit the patient
When I type in a reason
When I deleteforever the patient
When I double confirm delete forever
Then the message says Patient deleted forever

When I search for the last created name
Then no patients are returned