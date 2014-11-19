Scenario: Edit patient attributes

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Manage Patients link
When I search for a  patient horatio
And choose the patient
And enter birthplace, citizenship, health district and race as Kenya, Kenyan, Nairobi and human respectively
And I click on the button Save Patient
Then the patient attributes should be updated