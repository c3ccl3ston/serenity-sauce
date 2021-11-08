package com.saucelabs.cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.IntStream;

public class StepDefinitions {
    private WebDriverWait wait;

    @Before
    public void setUp(Scenario scenario) {
        wait = new WebDriverWait(Serenity.getDriver(), 10);
        ((JavascriptExecutor) Serenity.getDriver()).executeScript("sauce:job-name=" + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        ((JavascriptExecutor) Serenity.getDriver()).executeScript("sauce:job-result=" + !scenario.isFailed());
    }

    @Given("^I go to the login page$")
    public void go_to_login_page() {
        Serenity.getDriver().get("https://www.saucedemo.com");
    }

    @Given("I am on the inventory page")
    public void go_to_the_inventory_page() {
        Serenity.getDriver().get("https://www.saucedemo.com/inventory.html");
    }

    @When("I login as a valid user")
    public void login_as_valid_user() {
        login("standard_user", "secret_sauce");
    }

    @When("I login as an invalid user")
    public void login_as_invalid_user() {
        login("doesnt_exist", "secret_sauce");
    }

    /**
     * Use this method to send any number of login/password parameters, to test different edge cases or roles within
     * the software. This method exists to show an example of how steps can call other parameterized methods.
     *
     * @param username The user name to login with
     * @param password The password to use (for testing the password field
     */
    private void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        Serenity.getDriver().findElement(By.id("user-name")).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        Serenity.getDriver().findElement(By.id("password")).sendKeys(password);

        Serenity.getDriver().findElement(By.className("btn_action")).click();
    }

    @When("^I add (\\d+) items? to the cart$")
    public void add_items_to_cart(int items) {
        By itemButton = By.className("btn_primary");

        IntStream.range(0, items).forEach(i -> {
            wait.until(ExpectedConditions.elementToBeClickable(Serenity.getDriver().findElement(itemButton)));
            Serenity.getDriver().findElement(itemButton).click();
        });
    }

    @And("I remove an item")
    public void remove_an_item() {
        By itemButton = By.className("btn_secondary");

        wait.until(ExpectedConditions.elementToBeClickable(Serenity.getDriver().findElement(itemButton)));
        Serenity.getDriver().findElement(itemButton).click();
    }

    @Then("I have (\\d) items? in my cart")
    public void one_item_in_cart(Integer items) {
        String expected_items = items.toString();

        By itemsInCart = By.className("shopping_cart_badge");

        wait.until(ExpectedConditions.elementToBeClickable(Serenity.getDriver().findElement(itemsInCart)));
        Assert.assertEquals(Serenity.getDriver().findElement(itemsInCart).getText(), expected_items);
    }

    @Then("The item list is not displayed")
    public void item_list_is_not_diplayed() {
        Assert.assertEquals(Serenity.getDriver().findElements(By.id("inventory_container")).size(), 0);
    }

    @Then("The item list is displayed")
    public void item_list_is_diplayed() {
        Assert.assertTrue(Serenity.getDriver().findElement(By.id("inventory_container")).isDisplayed());
    }
}