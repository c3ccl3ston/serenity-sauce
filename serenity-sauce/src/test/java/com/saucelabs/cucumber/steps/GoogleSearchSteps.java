package com.saucelabs.cucumber.steps;

import com.saucelabs.cucumber.pages.GooglePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleSearchSteps extends BaseSteps {
    GooglePage googlePage;

    @When("^I type query as \"([^\"]*)\"$")
    public void search_google_for(String searchWord) throws Throwable {
        googlePage.open();
        googlePage.searchForString(searchWord);
    }

    @Then("^I submit$")
    public void thenSubmit() throws Throwable {
        googlePage.submitForm();
    }

    @Then("^I should see title \"([^\"]*)\"$")
    public void matchTitle(String matchTitle) throws Throwable {
        googlePage.titleShouldMatch(matchTitle);
    }
}
