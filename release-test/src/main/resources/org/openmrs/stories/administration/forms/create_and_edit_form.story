Scenario: create and edit a form

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Manage Forms link
Then take me to OpenMRS as heading

When I click on the Add Form link
When I enter TestForm as the form name
And I enter Test Description as the description
And I enter 1.0 as the Version
And I click on the button Save Form
Then display message Form saved

When I click on the TestForm link
And I update the form name TestForm
And I enter Test Description 2 as the description
And I toggle the Published checkbox
And I click on the button Save Form
Then display message Form saved