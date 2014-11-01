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

import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Finders.button;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;

import static org.junit.Assert.*;

public class PersonSteps extends Steps {
	
	public static String lastGivenName;
    public static String lastFamilyName;
    
	public PersonSteps(WebDriver driver) {
		super(driver);
	}	
	
	@When("I enter a random name as the name")
	public void enterARandomNameAsTheName() {
	    String givenName = RandomStringUtils.randomAlphabetic(10);
	    String familyName = RandomStringUtils.randomAlphabetic(10);
	    lastGivenName = givenName;
	    lastFamilyName = familyName;
	    String name = lastGivenName + " " + lastFamilyName;
		type(name, into(textbox().with(attribute("id", equalTo("personName")))));
	}
	
	@When("I enter a random age, and $gender as gender")
	public void enterARandomAgeAndGender(String gender) {
		// Generate a random integer to be the age of this person
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		String age = Integer.toString(randomInt);
		type(age, into(textbox().with(attribute("id", equalTo("age")))));
		
		// Select the specified gender
		if(gender.equalsIgnoreCase("Female"))
		{
			getWebDriver().findElement(By.id("gender-F")).click();
		}
		else if(gender.equalsIgnoreCase("Male"))
		{
			getWebDriver().findElement(By.id("gender-M")).click();
		}
	}
	
	@When("I click on $createPerson button")
	public void clickOnCreatePersonButton(String createPersonLabel) {
		clickOn(button(createPersonLabel));
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
