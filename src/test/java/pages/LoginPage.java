package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath ="//button[@aria-label='Sign In']" )
    public WebElement SignIn;

    @FindBy(xpath ="//input[@placeholder='Email']" )
    public WebElement username;

    @FindBy(xpath ="//input[@placeholder='Password']" )
    public WebElement password;

    @FindBy(xpath="//*[@id=\"details-button\"]")
    public WebElement gelismisButon;

    @FindBy(xpath="//*[@id=\"proceed-link\"]")
    public WebElement proceedLink;

    @FindBy(xpath="//span[normalize-space()='Ana Sayfa']")
    public WebElement dashboardUrl;

// LoginPage.java dosyasının içine, diğer @FindBy tanımlarının yanına ekleyin:

    @FindBy(id = "login-error")
    public WebElement loginErrorText;
}