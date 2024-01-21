package elemica.pages;
import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class OrderPage extends BasePage {
    public OrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//button[contains(@data-name,'tms.userInfo')]/span")
    private WebElement btnUserInfo;
    @FindBy(xpath = "//label[contains(text(),'Welcome')]")
    private WebElement txtWelcome;
    @FindBy(id = "cdk-accordion-child-0")
    private WebElement btnOrderIntake;
    @FindBy(xpath = "//div[contains(@class,'overlay-pane')]//a/span[contains(text(),'Orders')]")
    private WebElement btnOrders;
    @FindBy(xpath = "(//div[@class='main-component']//span)[1]")
    private WebElement lnkFilters;
    @FindBy(xpath = "//input[@placeholder='Order Number']")
    private WebElement txtboxOrderNumber;
    @FindBy(xpath = "//span[contains(text(),'Search')]")
    private WebElement btnSearch;
    @FindBy(xpath = "//a[contains(@class,'identifier-value')]")
    private List<WebElement> txtOrderNumberValues;
    @FindBy(xpath = "//div[contains(@class,'header-text')]/*[text()='Order Details']")
    private WebElement hdrOrderDetails;
    @FindBy(xpath = "//div[contains(text(),'ATTACHMENTS')]")
    private WebElement hdrAttachments;
    @FindBy(xpath = "//button[contains(@class,'add-button')]")
    private WebElement btnAddAttachment;
    @FindBy(xpath = "//span[text()='browse your device']")
    private WebElement lnkBrowseYourDevice;
    @FindBy(xpath = "//span[contains(text(),'Ok')]")
    private WebElement btnOk;
    @FindBy(xpath = "//a[contains(@class,'identifier-value')]")
    private List<WebElement> lnkFileUploaded;

    public void verifyLoginIsSuccessful() {
        Assert.assertTrue(txtWelcome.isDisplayed(), "Login is not successful");
        System.out.println("Login is successful and home page is displayed");
    }

    public void navigateToOrderScreen() {
        Actions actions = new Actions(driver);
        actions.moveToElement(btnOrderIntake).build().perform();
        btnOrders.click();
        Assert.assertTrue(lnkFilters.isDisplayed(), "Failed to navigate to Orders screen");
        System.out.println("Navigate to Orders screen");
    }

    public void filterWithOrderNumber(String orderNumber) {
        lnkFilters.click();
        Assert.assertTrue(txtboxOrderNumber.isDisplayed(), "Order number text box is not displayed");
        txtboxOrderNumber.click();
        txtboxOrderNumber.sendKeys(orderNumber);
        btnSearch.click();
    }

    public void verifyResults(String orderNumber) {
        int count = 0;
        for(WebElement orderNum : txtOrderNumberValues) {
            Assert.assertEquals(orderNum.getText(), orderNumber, "Order number is not matching");
            count++;
        }
        System.out.println("Results are displayed and the count is: "+count);
    }

    public void clickOrderNumber() {
        txtOrderNumberValues.get(0).click();
        Assert.assertTrue(hdrOrderDetails.isDisplayed(), "Order details is not displayed");
    }

    public void clickTab(String attachments) {
        String strXpath =  "//div[contains(text(),'####')]";
        strXpath = strXpath.replace("####", attachments.toUpperCase());
        WebElement tabName = driver.findElement(By.xpath(strXpath));
        tabName.click();
        Assert.assertTrue(btnAddAttachment.isDisplayed(), "Failed to click on Attachments tab");
    }

    public void uploadAttachment(String fileName) {
        btnAddAttachment.click();
        String filePath = System.getProperty("user.home")+"/IdeaProjects/ElemicaTask1/src/test/resources/FileAttachments/"+fileName;
        lnkBrowseYourDevice.sendKeys(filePath);
    }

    public void downloadAndVerifyTheAttachment(String fileName) {
        String uploadedFileName = lnkFileUploaded.get(lnkFileUploaded.size()-1).getText();
        Assert.assertEquals(fileName, uploadedFileName, "File upload is not successful");
        lnkFileUploaded.get(lnkFileUploaded.size()-1).click();
        try {
            Thread.sleep(5000); // Wait for 5 seconds (Adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fileDownloadedPath = System.getProperty("user.home")+"/Downloads";
        boolean isFileDownloaded = isFileDownloaded(fileDownloadedPath, fileName);
        if (isFileDownloaded) {
            System.out.println("File downloaded successfully!");
        } else {
            System.out.println("File download failed or file not found!");
        }
    }

    private static boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }
}
