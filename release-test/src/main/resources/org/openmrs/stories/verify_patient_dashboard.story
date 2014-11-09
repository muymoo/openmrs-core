Scenario: Verify  Patient  dashboard Header

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Find/Create Patient link

Given I am on Find/Create Patient Page with Find Patient as title
When I search for a patient Hora
And view that patient's dashboard
Then the dashboard header should contain name, age, bmi, CD4, regimens, last encounter, Old identification number and OpenMRS identification number