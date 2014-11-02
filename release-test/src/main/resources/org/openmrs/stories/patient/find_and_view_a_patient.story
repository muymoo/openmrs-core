Scenario: search for existing patient

GivenStories: org/openmrs/stories/patient/create_a_basic_patient.story

When I click on the Find/Create Patient link
When I search for the last created name
Then what is returned is the last created name


Scenario: then view that patient's dashboard

When I view that patient's dashboard
Then the dashboard name should match the last created name