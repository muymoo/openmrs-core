Scenario: Add a visit attribute type

GivenStories: org/openmrs/stories/go_to_admin_page.story

When I click on the Manage Visit Attribute Types link
Then take me to OpenMRS as heading

When I click on the Add Visit Attribute Type link
When I enter Followup as the name
And I enter For Review of Results as the description
And I enter 0 for Min Occurs
And I enter 1 for Max Occurs
And I select Boolean as the Datatype
And I enter test123 as the Datatype Configuration
And I select Default as the Preferred Handler
And I enter test123 as the Handler Configuration
And I click on the button Save Visit Attribute Type
Then display message Visit Attribute Type saved