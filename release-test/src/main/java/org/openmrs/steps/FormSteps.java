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

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;

public class FormSteps extends Steps {

	public FormSteps(WebDriver driver) {
		super(driver);
	}
	
	@When("I enter $value as the form name")
	public void enterValueAsTheFormName(String formName) {;
		type(formName, into(textbox().with(attribute("name", equalTo("name")))));
	}
	
	@When("I update the form name $formName")
	public void updateTheFormName(String formName) {
		// Generate a random integer to append to the visit type
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		String value = formName + Integer.toString(randomInt);
		type(value, into(textbox().with(attribute("name", equalTo("name")))));
	}
	
	@When("I enter $description as the description")
	public void enterDescriptionAsTheDescription(String description) {
		WebElement textarea = getWebDriver().findElement(By.name("description"));
		textarea.sendKeys(description);
	}
	
	@When("I enter $value as the Version")
	public void enterValueAsTheVersion(String value) {
		type(value, into(textbox().with(attribute("name", equalTo("version")))));
	}
	
	@When("I toggle the Published checkbox")
	public void makeTheFormPublished() {
		getWebDriver().findElement(By.name("published")).click();
	}
	
	@Then("take me to $title as heading")
	public void takeMeToHeading(String heading) throws InterruptedException	{
		Thread.sleep(1000);
        assertEquals(heading, getTitle());
	}
}
