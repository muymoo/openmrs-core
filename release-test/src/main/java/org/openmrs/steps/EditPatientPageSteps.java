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
import static org.openqa.selenium.lift.Finders.button;
import static org.openqa.selenium.lift.Finders.div;
import static org.openqa.selenium.lift.Finders.textbox;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;
import junit.framework.Assert;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPatientPageSteps extends Steps {

	public EditPatientPageSteps(WebDriver driver) {
		super(driver);
	}
	
	@When("I delete the patient")
	public void deletePatient() {
	    waitAndClickOn(button("Delete Patient"));
	}
	
	@When("I deleteforever the patient")
	public void deleteforever() {
        WebElement btn = driver.findElement(By.id("deletePatientButton"));
        btn.click();
    }
	
	@When("I double confirm delete forever")
	public void confirmDeleteForever() {
	    WebElement btn = driver.findElement(By.cssSelector("#deletePatientDiv input[value=\"Delete Patient Forever\"]"));
        btn.click(); 
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            //exception handling
        }
	}
	
	@When("I type in a reason")
	public void typeInReason() {
       type("They left the practice",
                into(textbox().with(
                        attribute("name", equalTo("voidReason")))));
	}

	@Then("I get an error")
	public void getError() {
	    assertPresenceOf(div().with(
	            attribute("id", equalTo("openmrs_error"))).with(
                        text(containsString("Delete reason cannot be empty"))));
	}
	
	@Then("the message says $message")
    public void getMessage(String message) {
        assertPresenceOf(div().with(
                attribute("id", equalTo("openmrs_msg"))).with(
                        text(containsString(message))));
    }
	
	@When("I click Restore Patient")
	public void restorePatient() {
        WebElement btn = driver.findElement(By.cssSelector("input[value=\"Restore Patient\"]"));
        btn.click();
	}	
	
	@When("enter birthplace, citizenship, health district and race as $birthPlace, $citizenship, $district and $race respectively")
	public void editAttributes(String birthPlace, String citizenship,
			String district, String race) {
		type(birthPlace,
				into(textbox().with(attribute("name", equalTo("2")))));
		type(citizenship,
				into(textbox().with(attribute("name", equalTo("3")))));
		type(district,
				into(textbox().with(attribute("name", equalTo("6")))));
		type(race,
				into(textbox().with(attribute("name", equalTo("1")))));
	}
	
	@Then("the birthplace, citizenship, health district and race are $birthPlace, $citizenship, $district and $race respectively")
    public void checkAttributes(String birthPlace, String citizenship,
            String district, String race) throws InterruptedException {
        Thread.sleep(2000);
        
        WebElement info = driver.findElement(By.id("pInformationBox"));
        
        Assert.assertEquals(birthPlace, info.findElement(
                By.cssSelector("input[name=\"2\"]")).getAttribute("value"));
        Assert.assertEquals(citizenship, info.findElement(
                By.cssSelector("input[name=\"3\"]")).getAttribute("value"));
        Assert.assertEquals(district, info.findElement(
                By.cssSelector("input[name=\"6\"]")).getAttribute("value"));
        Assert.assertEquals(race, info.findElement(
                By.cssSelector("input[name=\"1\"]")).getAttribute("value"));
    }
//
//	@When("save")
//	public void save() {
//		clickOn(button().with(attribute("id", equalTo("saveButton"))));
//	}
//
	@Then("the patient attributes should be updated")
	public void verifyAttributesUpdated() {
		assertPresenceOf(div().with(text(equalTo("Patient saved"))));
	}

}
