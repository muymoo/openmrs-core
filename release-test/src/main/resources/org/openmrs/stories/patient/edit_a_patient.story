Scenario: edit the patient and verify saves

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I edit the patient
And enter birthplace, citizenship, health district and race as Kenya, Kenyan, Nairobi and human respectively
And I click on the button Save Patient
Then the patient attributes should be updated

When I click on the Find/Create Patient link
When I search for the last created name
Then what is returned is the last created name

When I view that patient's dashboard
When I edit the patient
Then the birthplace, citizenship, health district and race are Kenya, Kenyan, Nairobi and human respectively
