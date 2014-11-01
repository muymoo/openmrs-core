Scenario: create a new person

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Manage Persons link
Then take me to OpenMRS as heading

When I click on the Create Person link
When I enter a random name as the name
And I enter a random age, and Female as gender
And I click on Create Person button

And I click on Save Person button
Then display message Person saved