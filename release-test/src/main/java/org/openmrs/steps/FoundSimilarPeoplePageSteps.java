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
package org.openmrs.steps;

import org.jbehave.core.annotations.Then;
import org.openmrs.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import junit.framework.Assert;
import static org.junit.Assert.*;

public class FoundSimilarPeoplePageSteps extends Steps {
    public FoundSimilarPeoplePageSteps(WebDriver driver) {
        super(driver);
    }
    
    @Then("I am taken to $title screen")
    public void takenToTitleScreen(String title) {
        assertEquals(getTitle(), "OpenMRS - " + title);
    }
    
    @Then("I see the title Found Similar People")
    public void seeTheTitleFoundSimilarPeople() {
        assertEquals("Found Similar People", driver.findElement(By.tagName("h2")).getText());
    }
    
    @Then("I see another similar patient listed as $firstName $lastName")
    public void seeAnotherSimilarPatientListedAsSimilarPatient(String firstName, String lastName) throws InterruptedException {
        List<WebElement> rows = getRowsOfResults(".openmrsSearchTable tbody");
        Assert.assertTrue(rows.size() >= 1);

        // From the first row returned, get all the column-values in that row
        List<WebElement> cells = rows.get(0).findElements(By.tagName("td"));
        
        // Person's Given name
        String givenName = cells.get(2).getText();
        
        // Person's Family Name
        String familyName = cells.get(4).getText();

        assertEquals(firstName + " " + lastName, givenName + " " + familyName);
    }
    
}
