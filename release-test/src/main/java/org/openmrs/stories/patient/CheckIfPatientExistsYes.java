/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.stories.patient;

import static java.util.Arrays.asList;

import java.util.List;

import org.openmrs.Steps;
import org.openmrs.Story;
import org.openmrs.steps.FindPatientPageSteps;
import org.openmrs.steps.FoundSimilarPeoplePageSteps;
import org.openmrs.steps.LoginPageSteps;


public class CheckIfPatientExistsYes extends Story {

    @Override
    public List<Steps> includeSteps() {
        return asList(new LoginPageSteps(driver), 
                new FindPatientPageSteps(driver),
                new FoundSimilarPeoplePageSteps(driver));
    }
}
