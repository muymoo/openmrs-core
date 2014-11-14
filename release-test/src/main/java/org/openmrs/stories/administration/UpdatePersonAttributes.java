package org.openmrs.stories.administration;

import org.openmrs.Steps;
import org.openmrs.Story;
import org.openmrs.steps.AdminSteps;
import org.openmrs.steps.LoginPageSteps;
import org.openmrs.steps.UpdatePersonAttributesSteps;

import java.util.List;

import static java.util.Arrays.asList;


public class UpdatePersonAttributes extends Story {
    @Override
	public List<Steps> includeSteps() {
		return asList(new LoginPageSteps(driver), new AdminSteps(driver), new UpdatePersonAttributesSteps(driver));
	}
}
