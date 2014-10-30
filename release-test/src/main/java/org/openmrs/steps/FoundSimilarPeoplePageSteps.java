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


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.link;
import static org.openqa.selenium.lift.Finders.title;
import static org.openqa.selenium.lift.Finders.button;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.text;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.junit.Assert.*;

public class FoundSimilarPeoplePageSteps extends Steps {
    public FoundSimilarPeoplePageSteps(WebDriver driver) {
        super(driver);
    }
    
    @Then("I am taken to $title screen")
    public void takenTo(String title) {
        assertEquals(getTitle(), "OpenMRS - " + title);
    }
    
    @Then("I see the title Found Similar People")
    public void title() {
        assertEquals("Found Similar People", driver.findElement(By.tagName("h2")).getText());
    }
    
    @Then("I see patient 101-6 as a similar patient")
    public void patient() throws InterruptedException {
        List<WebElement> rows = getRowsOfResults(".openmrsSearchTable tbody");
        assertEquals(rows.size(), 1);

        List<WebElement> cells = rows.get(0).findElements(By.tagName("td"));
        String id = cells.get(1).getText();

        assertEquals(id, "101-6");
    }
    
}
