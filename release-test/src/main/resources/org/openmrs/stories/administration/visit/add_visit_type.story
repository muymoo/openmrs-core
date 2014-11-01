Scenario: Add a visit type

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Manage Visit Types link
Then take me to OpenMRS as heading

When I click on the Add Visit Type link
When I enter Hospitalization as the name
And I enter For Surgery as the description
And I click on the button Save Visit Type
Then display message Visit Type saved