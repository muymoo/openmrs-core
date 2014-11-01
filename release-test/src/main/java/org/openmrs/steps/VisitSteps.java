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

import java.util.Random;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;

import static org.junit.Assert.*;

public class VisitSteps extends Steps {

	public static String lastGivenName;
	public static String lastFamilyName;

	public VisitSteps(WebDriver driver) {
		super(driver);
	}

	@When("I enter $visitType as the name")
	public void enterARandomNameAsTheName(String visitType) {
		// Generate a random integer to append to the visit type
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		String value = visitType + Integer.toString(randomInt);
		type(value, into(textbox().with(attribute("name", equalTo("name")))));
	}
	
	@When("I enter $description as the description")
	public void enterDescriptionAsTheDescription(String description) {
		WebElement textarea = getWebDriver().findElement(By.name("description"));
		textarea.sendKeys(description);
	}
	
	@Then("take me to $title as heading")
	public void takeMeToHeading(String heading) throws InterruptedException	{
		Thread.sleep(1000);
        assertEquals(heading, getTitle());
	}
	
	@Then("display message $message")
    public void verifyDisplayedMessage(String message) {
        waitAndAssertFor(div().with(text(containsString(message))));
    }
}
