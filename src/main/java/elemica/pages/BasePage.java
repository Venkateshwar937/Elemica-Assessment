package elemica.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver = null;
    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    @FindBy(id = "tms-login-username")
    private WebElement txtboxUsername;
    @FindBy(id = "tms-login-password")
    private WebElement txtboxPassword;
    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    private WebElement btnSignIn;

    public void launchElemicaApplication() {
        driver.get("https://elemica-acc.eyefreight.com/login.jsp");
        System.out.println("Launched the URL");
    }

    public void loginToTheApplication(String username, String password) {
        txtboxUsername.sendKeys(username);
        txtboxPassword.sendKeys(password);
        btnSignIn.click();
    }
}
