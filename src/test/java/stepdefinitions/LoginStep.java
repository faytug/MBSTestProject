package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.junit.Assert.assertEquals;

public class LoginStep {

    private WebDriver driver = Driver.getDriver();

    LoginPage LoginPage = new LoginPage();
    Actions actions = new Actions(Driver.getDriver());

    @Given("user goes to Swagger page")
    public void user_goes_to_swagger_page() {
        driver.get(ConfigReader.getProperty("swaggerUrl"));
        try{
            Thread.sleep(3000);
            LoginPage.gelismisButon.click();
            Thread.sleep(3000);
            actions.moveByOffset(0,200).perform();
            LoginPage.proceedLink.click();
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("Advanced Link ve Proceed Link istenmedi");
        }
    }
    @When("user goes to login page")
    public void user_goes_to_login_page() {
        driver.get(ConfigReader.getProperty("loginUrl"));
    }
    @And("the user enters valid username")
    public void the_user_enters_valid_username() {
        LoginPage.username.clear();
        LoginPage.username.sendKeys(ConfigReader.getProperty("validUsername"));
    }
    @And("the user enters valid password")
    public void the_user_enters_valid_password() {
        LoginPage.password.clear();
        LoginPage.password.sendKeys(ConfigReader.getProperty("validPassword"));
    }

    @When("user clicks on SignIn button")
    public void user_clicks_on_sign_ın_button() throws InterruptedException {
        Thread.sleep(3000);
        LoginPage.SignIn.click();
    }

    @Then("user should be redirected to dashboard page")
    public void user_should_be_redirected_to_dashboard_page() {
        assertEquals(ConfigReader.getProperty("dashboardUrl"), driver.getCurrentUrl());
    }

    @And("the user enters invalid username")
    public void the_user_enters_invalid_username() {
        LoginPage.username.clear();
        LoginPage.username.sendKeys(ConfigReader.getProperty("invalidUsername"));
    }
    @And("the user enters invalid password")
    public void the_user_enters_invalid_password() {
        LoginPage.password.clear();
        LoginPage.password.sendKeys(ConfigReader.getProperty("invalidPassword"));
    }
    @Then("An error message should be displayed")
    public void an_error_message_should_be_displayed() {
// Kullanıcı dashboard'a gitmemeli
        Assert.assertFalse(driver.getCurrentUrl().contains("dashboard"));
    }
    @Then("User should not be redirected to the dashboard")
    public void user_should_not_be_redirected_to_the_dashboard() {
// Hata mesajı görünmeli
        WebElement errorMsg = driver.findElement(By.id("login-error"));
        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals("Invalid username or password", errorMsg.getText());
    }
}
