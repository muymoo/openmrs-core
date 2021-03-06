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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.find.HtmlTagFinder;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Finders.*;
import static org.openqa.selenium.lift.Matchers.text;
import static org.openqa.selenium.lift.match.AttributeMatcher.attribute;

import static org.junit.Assert.*;

public class CreateObservationSteps extends Steps {
    public CreateObservationSteps(WebDriver driver) {
        super(driver);
    }

    @Then("take me to Observation Management Page with Observation Management as heading")
    public void verifyManagementPage() {
        waitAndAssertFor(div().with(text(containsString("Observation Management"))));
    }


    @Given("I am on the Observation Management Page")
    public void onFindObservationManagementPage() {
    	verifyManagementPage();
    }


    @Then("take me to Add Observation page with $heading as heading and has a button with label $buttonText")
    public void verifyAddObservationPage(String heading, String buttonText) {
        waitAndAssertFor(div().with(text(containsString(heading))));
        waitAndAssertFor(button("Save Observation"));

    }

    @Given("I am on the Add Observation page")
    public void givenIamOnAddObservationPage() {
    	assertEquals("OpenMRS", getTitle());
    }

    @When("I type $name as person")
    public void enterPersonName(String name) {
        //type(name, into(textbox().with(attribute("id", equalTo("person_id_selection")))));
        //waitAndClickOn(finderByXpath("//li[@class='ui-menu-item'][1]"));
        WebElement personSelection = driver.findElement(By.id("person_id_selection"));
        personSelection.sendKeys(name);
        waitFor(finderByXpath("//li[@class='ui-menu-item'][1]"));
        personSelection.sendKeys(Keys.TAB);
    }
    
    @When("I select Unknown Location as Location with index $index")
    public void selectLocation(int index) {
        selectAValueInDropDownByXpath("//table[@id=\'obsTable\']/tbody/tr[4]/td/select[@id=\'location\']").selectByIndex(index);
    }

    @When("I type $date as Observation Date")
    public void enterObservationDate(String date) {
        //type(date, into(textbox().with(attribute("id", equalTo("obsDatetime")))));
    	WebElement obsDateSelection = driver.findElement(By.id("obsDatetime"));
    	obsDateSelection.sendKeys(date);
    	obsDateSelection.sendKeys(Keys.TAB);
    }

    @When("I type $conceptQuestion as Concept Question")
    public void enterConceptQuestion(String conceptQuestion) {
        type(conceptQuestion, into(textbox().with(attribute("id", equalTo("conceptId_selection")))));
    	waitFor(finderByXpath("//html/body/ul[4]/li[1]/a/span/span"));
        clickOn(finderByXpath("//html/body/ul[4]/li[1]/a/span/span"));
    }

    @When("I type $conceptAnswer as Concept Answer")
    public void enterConceptAnswer(String conceptAnswer) {
        type(conceptAnswer, into(textbox().with(attribute("name", equalTo("valueNumeric")))));
    }
    
    @When("I choose $conceptAnswer as the Concept Answser")
    public void chooseConceptAnswer(String conceptAnswer) {  	
    	selectFrom(conceptAnswer, "valueBooleanSelect");
    }

    @Then("display message Observation saved")
    public void verifySuccessMessage() {
        waitAndAssertFor(div().with(text(containsString("Observation saved"))));
    }

}
