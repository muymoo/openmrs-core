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

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Finders.cell;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.link;
import static org.openqa.selenium.lift.Matchers.text;
import static org.openqa.selenium.lift.match.AttributeMatcher.attribute;

import java.util.List;

import junit.framework.Assert;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPageSteps extends Steps {

	public DashboardPageSteps(WebDriver driver) {
		super(driver);
	}

	@Then("the dashboard name should match the last created name")
	public void matchLastName() {
	    String familyName = CreatePatientPageSteps.lastFamilyName;
	    String givenName = CreatePatientPageSteps.lastGivenName;
	    assertPresenceOf(div().with(
               attribute("id",equalTo("patientHeaderPatientName"))).with(
                       text(equalTo(givenName + " " + familyName))));
	}

	@Then("address1 should match $address")
	public void matchAddress(String address) {
        clickOn(link().with(attribute("id", equalTo("patientDemographicsTab"))));
        List<WebElement> cells = driver.findElements(By.cssSelector("table.personAddress tr td"));
        Assert.assertEquals(cells.get(1).getText(), address);
	}
	
	@Then("age should match $age")
	public void matchAge(String age) {
        assertPresenceOf(cell().with(
                attribute("id",equalTo("patientHeaderPatientAge"))).with(
                        text(containsString(age+" yrs"))));
	}
	
	@When("I edit the patient in the short form")
    public void editPatientInShortForm() throws InterruptedException {
        clickOn(link("Demographics"));
        clickOn(link("Edit this Patient (Short Form)"));
    }
	
	@When("I edit the patient")
    public void editPatient() throws InterruptedException {
        clickOn(link("Demographics"));
        clickOn(link("Edit this Patient"));
    }
	
	@Then("the information should be saved")
    public void verifySavedInformation() {
        assertPresenceOf(div().with(text(equalTo("Patient saved"))));
    }
}
