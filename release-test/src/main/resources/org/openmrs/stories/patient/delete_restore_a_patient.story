Scenario: create a patient then delete the patient with reason they are deleted

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I edit the patient
When I type in a reason
When I delete the patient
Then the message says Patient deleted

When I click Restore Patient
Then the message says Patient restored

When I click on the Find/Create Patient link
When I search for the last created name
Then what is returned is the last created name