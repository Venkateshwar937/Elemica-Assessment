package stepdefinitions;

import elemica.pages.BasePage;
import elemica.pages.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.TestRunner;


public class OrderStepDefinition {
    private WebDriver driver;
    BasePage basePage;
    OrderPage orderPage;
    public OrderStepDefinition() {
        //driver = TestNgRunner.getDriver();
        basePage = new BasePage(driver);
    }

    @Given("^User launches Elemica application$")
    public void userlaunchesElemicaApplication() {
       basePage.launchElemicaApplication();
    }

    @And("User logs into the application with credentials as (.*) and (.*)$")
    public void userLogsIntoTheApplicationWithCredentialsAsAnd(String username, String password) {
        basePage.loginToTheApplication(username,password);
    }

    @Then("User verifies login is successful")
    public void userVerifiesLoginIsSuccessful() {
        orderPage.verifyLoginIsSuccessful();
    }

    @Given("User navigates to Orders screen")
    public void userNavigatesToOrdersScreen() {
        orderPage.navigateToOrderScreen();
    }

    @When("User filter the order with order number (.*)$")
    public void userFilterTheOrderWithOrderNumber(String orderNumber) {
        orderPage.filterWithOrderNumber(orderNumber);
    }

    @Then("User should be able to view the results based on (.*)$")
    public void userShouldBeAbleToViewTheResultsBasedOn(String orderNumber) {
        orderPage.verifyResults(orderNumber);
    }

    @When("User clicks on the order number")
    public void userClicksOnTheOrderNumber() {
        orderPage.clickOrderNumber();
    }

    @And("User clicks the (.*) tab$")
    public void userClicksTheTab(String attachments) {
        orderPage.clickTab(attachments);
    }

    @And("User uploads an attachment with (.*)$")
    public void userUploadsAnAttachmentWith(String fileName) {
        orderPage.uploadAttachment(fileName);
    }

    @Then("User downloads the same file and verify the (.*)$)")
    public void userDownloadsTheSameFileAndVerifyThe(String fileName) {
        orderPage.downloadAndVerifyTheAttachment(fileName);
    }
}
