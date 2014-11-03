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
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;

import java.util.List;

import junit.framework.Assert;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@When("I click dashboard $linktitle")
	public void clickLinkTitle(String linktitle) {
        clickOn(link(linktitle));
	}
	
	@When("I click the Save button")
    public void clickOnSave() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-addActiveListAllergy\"] span.ui-button-text"));
	    buttons.get(0).click();
    }
	
	@When("I click the Cancel button")
    public void clickOnCancel() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-addActiveListAllergy\"] span.ui-button-text"));
        buttons.get(1).click();
    }
	
	@When("I click the problem Save button")
    public void clickOnProblemSave() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-addActiveListProblem\"] span.ui-button-text"));
        buttons.get(0).click();
    }
	
	@When("I click the program Enroll button")
    public void clickOnProgramEnroll() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-enrollInProgramDialog\"] span.ui-button-text"));
        buttons.get(0).click();
    }
	
	@When("I click the program Cancel button")
    public void clickOnProgramCancel() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-enrollInProgramDialog\"] span.ui-button-text"));
        buttons.get(1).click();
    }
	
	@Then("an alert is displayed and I hit ok")
	public void closeAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();
	}
    
    @When("I click the problem Cancel button")
    public void clickOnProblemCancel() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                "div[aria-labelledby=\"ui-dialog-title-addActiveListProblem\"] span.ui-button-text"));
        buttons.get(1).click();
    }
	
	@When("I click on Cancel")
    public void clickCancel () {
        WebElement btn = driver.findElement(By.cssSelector("button span:contains('Cancel')"));
        btn.click();
    }
	
	@When("I enter an allergen $allergen")
	public void enterAllergen(String allergen) {
	    type(allergen,
                into(textbox().with(
                        attribute("id", equalTo("allergy_concept")))));
	}
	    
	@When("I enter a problem $problem")
    public void enterProblem(String problem) {
        type(problem,
                into(textbox().with(
                        attribute("id", equalTo("problem_concept")))));
    }
	
	@When("I choose HIV program")
	public void chooseHIV() {
	    Select select = new Select(driver.findElement(By.id("programSelector")));
	    select.selectByVisibleText("HIV Program");
	}
	
	@When("I set the enrollment date to $date")
	public void setDate(String date) {
	    type(date,
                into(textbox().with(
                        attribute("id", equalTo("dateEnrolled")))));
	}
	
	@Then("the allergy error is displayed")
	public void allergyError() {
	    WebElement error = driver.findElement(By.id("allergyError"));
	    Assert.assertEquals("Allergen required", error.getText());
	}
	
	@Then("the problem error is displayed")
	public void problemError() {
	    WebElement error = driver.findElement(By.id("problemError"));
	    Assert.assertEquals("Problem required", error.getText());
	}
	
	@Then("the enrollment date missing error is displayed")
	public void enrollmentErro() {
	    WebElement error = driver.findElement(By.id("openmrs_error"));
	    Assert.assertEquals("Enrollment date is required", error.getText());
	}
	
	@Then("the patient is in no programs")
	public void isInNoPrograms() {
	    WebElement box = driver.findElement(By.cssSelector("#patientPrograms"));
	    Assert.assertTrue(box.getText().contains("Not enrolled in any programs"));
	}
	
	@Then("the program is displayed in the table")
    public void isInPrograms() {
        WebElement box = driver.findElement(By.cssSelector("#patientPrograms"));
        Assert.assertFalse(box.getText().contains("Not enrolled in any programs"));
    }
	
	@When("I click on the HIV program")
	public void clickHIV() {
	    WebElement link = driver.findElement(By.linkText("HIV Program"));
	    link.click();
	}
	
	@Then("the edit program popup comes up")
	public void popup() {
	    WebElement popup = driver.findElement(By.id("editPatientProgramPopup"));
	    Assert.assertTrue(popup.isDisplayed());
	}
	
	@Then("it says the full name of the program")
	public void fullname() {
	    WebElement fullname = driver.findElement(By.id("programNameElement"));
	    Assert.assertEquals("HUMAN IMMUNODEFICIENCY VIRUS", fullname.getText());
	}
	
}
