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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.lift.Finders.button;
import static org.openqa.selenium.lift.Finders.radioButton;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreatePatientPageSteps extends Steps {

    public static String lastGivenName;
    public static String lastFamilyName;
    public static String lastId;
    
	public CreatePatientPageSteps(WebDriver driver) {
		super(driver);
	}

	@Then("take me to the Create Patient Page")
	public void verifyIfIamOnCreatePatientPage() {
		assertPresenceOf(button("Save"));
	}

	@Given("I am on the Create Patient Page")
	public void givenIamOnCreatePatientPage() {
		assertPresenceOf(button("Save"));
	}
	
	@When("I enter a random name as the name")
	public void enterName() {
	    String givenName = RandomStringUtils.randomAlphabetic(10);
	    String familyName = RandomStringUtils.randomAlphabetic(10);
	    lastGivenName = givenName;
	    lastFamilyName = familyName;
		type(lastGivenName,
				into(textbox().with(
						attribute("name", equalTo("personName.givenName")))));
		type(lastFamilyName,
		        into(textbox().with(
		                attribute("name", equalTo("personName.familyName")))));
	}

	@When("I enter $code as Identifier Code")
	public void enterIdentifierCode(String code) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		lastId = Integer.toString(randomInt);
		type(code + Integer.toString(randomInt),
				finderByXpath("//form[@id=\'patientModel\']//table[@id=\'identifiers\']//tr[@id=\'existingIdentifiersRow[0]\']/td[1]/input"));
	}

	@When("I select Old Identification Number as Identifier Type with index $id")
	public void enterIdentifierType(int id) {
		selectAValueInDropDownByXpath(
				"//form[@id=\'patientModel\']//table[@id=\'identifiers\']//tr[@id=\'existingIdentifiersRow[0]\']/td[2]/select[@id=\'identifiers0.identifierType\']")
				.selectByIndex(id);
	}

	@When("I select Unknown Location as location with index $id")
	public void selectIdentifierLocation(int id) {
		selectAValueInDropDownByXpath(
				"//form[@id=\'patientModel\']//table[@id=\'identifiers\']//tr[@id=\'existingIdentifiersRow[0]\']/td[3]/div[@id=\'initialLocationBox0\']/select[@id=\'identifiers0.location\']")
				.selectByIndex(id);
	}
	@When("I select preferred option")
	public void selectPreferredOption(){
		clickOn(radioButton().with(
                attribute("id", equalTo("identifiers[0].preferred"))));
	}

	@When("I enter $address as address")
	public void enterAddress(String address) {
		type(address,
				into(textbox().with(
						attribute("name", equalTo("personAddress.address1")))));
	}

	@When("I enter $country as country")
	public void enterCountryName(String country) {
		type(country,
				into(textbox().with(
						attribute("name", equalTo("personAddress.country")))));
	}
	
	@When("I click back")
	public void clickBack() throws InterruptedException {
        WebElement backButton = driver.findElement(By.cssSelector("input[value=\"Back\"]"));
        backButton.click();
	}

	@Then("take me to Patient dashboard page with title Patient Dashboard")
	public void verifyIfPatientIsCreated() throws InterruptedException {
	    Thread.sleep(1000);
		assertEquals("OpenMRS - Patient Dashboard", getTitle());
	}

}
