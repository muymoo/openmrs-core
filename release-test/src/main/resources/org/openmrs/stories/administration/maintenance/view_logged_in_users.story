Scenario: View currently logged in users

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the View Logged In Users link
Then take me to OpenMRS as heading

Then the following Users are displayed:
|name|
|systemid:admin|