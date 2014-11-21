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

import java.util.List;
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
	
	@When("I enter $value for Min Occurs")
	public void enterValueForMinOccurs(String value) {
		type(value, into(textbox().with(attribute("name", equalTo("minOccurs")))));
	}
	
	@When("I enter $value for Max Occurs")
	public void enterValueForMaxOccurs(String value) {
		type(value, into(textbox().with(attribute("name", equalTo("maxOccurs")))));
	}
	
	@When("I select $value as the Datatype")
	public void selectValueAsTheDatatype(String value) {
		WebElement datatypeCombobox = getWebDriver().findElement(By.name("datatypeClassname"));
		List<WebElement> options = datatypeCombobox.findElements(By.tagName("option"));
		for (WebElement option : options) {
	        if(value.equals(option.getText()))
	            option.click();
	    }
	}
	
	@When("I enter $value as the Datatype Configuration")
	public void enterValueAsTheDatatypeConfiguration(String value) {
		WebElement textarea = getWebDriver().findElement(By.name("datatypeConfig"));
		textarea.sendKeys(value);
	}
	
	@When("I enter $value as the Handler Configuration")
	public void enterValueAsTheHandlerConfiguration(String value) {
		WebElement textarea = getWebDriver().findElement(By.name("handlerConfig"));
		textarea.sendKeys(value);
	}
	
	@When("I select $value as the Preferred Handler")
	public void selectValueAsThePreferredHandler(String value) {
		WebElement datatypeCombobox = getWebDriver().findElement(By.name("preferredHandlerClassname"));
		List<WebElement> options = datatypeCombobox.findElements(By.tagName("option"));
		for (WebElement option : options) {
	        if(value.equals(option.getText()))
	            option.click();
	    }
	}
}
