Scenario: Change a global property

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Advanced Settings link
Then take me to OpenMRS as heading

When I type Test1 as name
When I type Test2 as value

And I click on the button Save
Then display message Global properties saved
