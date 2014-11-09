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
import static org.openqa.selenium.lift.Finders.title;
import static org.openqa.selenium.lift.Matchers.attribute;
import static org.openqa.selenium.lift.Matchers.text;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openmrs.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.lift.find.Finder;
import static org.junit.Assert.*;

public class LoginPageSteps extends Steps {

	public LoginPageSteps(WebDriver driver) {
		super(driver);
	}

	private boolean userAlreadyLoggedIn() {
		Finder<WebElement, WebDriver> f = finderByXpath(
				"//span[@id='userLoggedInAs']").with(
				text(containsString("Currently logged in")));
		return f.findFrom(getWebDriver()).size() > 0;
	}

	@Given("I am on the login page of OpenMRS")
	public void onLoginPage() {
		String port = System.getProperty("jetty.port", "8080");
		String url = "http://localhost:" + port + "/openmrs/initialsetup";
		goTo(url);
		waitAndAssertFor(button().with(attribute("value", equalTo("Log In"))));
	}

	@When("I enter a $valid username")
	public void enterUsername(String valid) {

		String username = "foo";
		if (valid.equals("valid")) {
			username = System.getProperty("openmrs_username", "admin");
		}

		type(username, into(textbox()
				.with(attribute("id", equalTo("username")))));
	}

	@When("I enter a $valid password")
	public void enterPassword(String valid) {
		String password = "bar";
		if (valid.equals("valid")) {
			password = System.getProperty("openmrs_password", "Admin123");
		}

		type(password,
				into(passwordtextbox().with(
						attribute("id", equalTo("password")))));
	}

	@When("I click the login button")
	public void clickLogIn() {
		clickOn(button());
	}

	@Then("take me to the $title screen and display welcome message for user $user")
	public void verifyPage(String title, String displayName) {
		assertEquals(getTitle(), "OpenMRS - " + title);
		assertPresenceOf(div().with(
				text(containsString("Hello, " + displayName + ". Welcome to"))));
	}

	@Then("I am not logged in")
	public void checkOnLoginPage() {
		assertFalse(userAlreadyLoggedIn());
	}

	@Then("I get the incorrect username/password error message")
	public void checkIncorrectUsernamePasswordMsg() {
		assertPresenceOf(div()
				.with(text(containsString("Invalid username/password. Please try again."))));
	}

	@When("I enter username and password as stored in system properties as $usernameProp and $passwordProp and click the 'Log In' button")
	public void logIn(String usernameProp, String passwordProp) {

		String username = System.getProperty(usernameProp, "admin");
		String password = System.getProperty(passwordProp, "Admin123");

		// (same as above reasoning)
		// this check is just in case a scenario has two dependencies and both
		// of them depend on the login_to_website story
		if (!userAlreadyLoggedIn()) {
			type(username,
					into(textbox().with(attribute("id", equalTo("username")))));
			type(password,
					into(passwordtextbox().with(
							attribute("id", equalTo("password")))));
			clickOn(button());
		}
	}

}
