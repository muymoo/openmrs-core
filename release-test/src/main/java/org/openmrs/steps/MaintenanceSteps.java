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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;
import org.openmrs.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.equalTo;
import static org.openqa.selenium.lift.Finders.title;
import static org.openqa.selenium.lift.Matchers.text;

import static org.junit.Assert.*;

public class MaintenanceSteps extends Steps {

	public MaintenanceSteps(WebDriver driver) {
		super(driver);
	}

	@Then("the following Users are displayed: $usersTable")
	public void theFollowingUsersAreDisplayed(ExamplesTable usersTable) {
		List<String> users = toUsers(usersTable);

		boolean foundUser = true;
		
		for (int i = 0; i < users.size(); i++) {
			String user = users.get(i);
			foundUser = doesValueExistInTable("box", user);
			if(foundUser == false)
				break;
		}
		
		Assert.assertEquals(true, foundUser);
	}

	private List<String> toUsers(ExamplesTable table) {
		List<String> users = new ArrayList();
		for (Map<String, String> row : table.getRows()) {
			String name = row.get("name");
			users.add(name);
		}
		return users;
	}

	private boolean doesValueExistInTable(String tableClassName, String targetValue)
	{
		boolean foundValue = false;
		
		// Get the table containing the users
		WebElement table = getWebDriver().findElement(By.className("box"));
				
		// Get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		
		// Iterate over them, getting the cells
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				if(cell.getText().equals(targetValue))
				{
					foundValue = true;
					break;
				}
			}
			
			if(foundValue == true)
				break;
		}
		
		return foundValue;
	}
}
