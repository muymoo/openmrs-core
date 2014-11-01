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

public class FindPatientPageSteps extends Steps {
    public FindPatientPageSteps(WebDriver driver) {
        super(driver);
    }
    
    @Given("I am on $title screen")
    public void onHomePage(String title) {
        assertEquals(getTitle(), "OpenMRS - " + title);
    }
    
    @When("I click on the $createPatient link")
    public void clickOnCreatePatientLink(String createPatient) {
        clickOn(link().with(text(equalTo(createPatient))));
    }

    @Then("take me to Find/Create Patient Page with $createPatientTitle as title")
    public void verifyCreatePatientPage(String createPatientTitle) {
        waitAndAssertFor(link().with(text(equalTo("Home"))));
        assertEquals("OpenMRS - " + createPatientTitle, getTitle());
    }

    @When("I type $search into the search box")
    public void typeSearchKeyword(String search) {
        type(search, into(textbox().with(attribute("id", equalTo("inputNode")))));
    }
    
    @When("I search for the last created name")
    public void searchLastName() {
        String search = CreatePatientPageSteps.lastGivenName + " " +
                CreatePatientPageSteps.lastFamilyName;
        type(search, into(textbox().with(attribute("id", equalTo("inputNode")))));
    }
    
    @Then("$first $last is returned")
    public void checkValidSearchResult(String first, String last) throws InterruptedException {
        List<WebElement> rows = getRowsOfResults("#openmrsSearchTable tbody");
        assertEquals(rows.size(), 1);

        List<WebElement> cells = rows.get(0).findElements(By.tagName("td"));
        String firstName = cells.get(1).getText();
        String lastName = cells.get(3).getText();

        assertEquals(first, firstName);
        assertEquals(last, lastName);
    }
    
    @Then("what is returned is the last created name")
    public void lastCreatedNameReturned() throws InterruptedException {
        String first = CreatePatientPageSteps.lastGivenName;
        String last = CreatePatientPageSteps.lastFamilyName;
        checkValidSearchResult(first, last);
    }

    @Then("no patients are returned")
    public void checkNoSearchResults() throws InterruptedException {
        List<WebElement> rows = getRowsOfResults("#openmrsSearchTable tbody");
        assertEquals(rows.size(), 1);
        assertEquals(rows.get(0).getText(), "No matching records found");
    }
    
    @When("I click Create Person")
    public void clickCreatePerson() throws InterruptedException {
        Thread.sleep(2000);
        WebElement createPersonButton = driver.findElement(By.cssSelector("input[value=\"Create Person\"]"));
        waitForVisibility(createPersonButton);
        createPersonButton.click();    
    }
    
    @Then("Person Name has an error")
    public void personNameError() {
        WebElement nameError = driver.findElement(By.id("nameError"));
        waitForVisibility(nameError);
        assertEquals("Please select a name", nameError.getText());
    }
    
    @Then("Person Name has an invalid error")
    public void personNameInvalidError() {
        WebElement nameError = driver.findElement(By.id("invalidNameError"));
        waitForVisibility(nameError);
        assertEquals("Invalid name", nameError.getText());
    }
    
    @Then("Age has an error")
    public void ageError() {
        assertEquals("Please select a valid birthdate or age", driver.findElement(By.id("birthdateError")).getText());
    }
    
    @Then("Gender has an error")
    public void genderError() {
        assertEquals("Please select a gender", driver.findElement(By.id("genderError")).getText());
    }
    
    @When("I type $name for name")
    public void typeName(String name) {
        type(name, into(textbox().with(attribute("id", equalTo("personName")))));
    }
    
    @When("I type $age for age")
    public void typeAge(String age) {
        type(age, into(textbox().with(attribute("id", equalTo("age")))));
    }

    @When("I choose Male")
    public void pickMale() {
        WebElement male = driver.findElement(By.id("gender-M"));
        male.click();
    }
    
    private void waitForVisibility(WebElement elt) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(elt));
    }

}
