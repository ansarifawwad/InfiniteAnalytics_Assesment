package org.example.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DownloadElectrolRolls {
    WebDriver driver;

    public DownloadElectrolRolls(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="ctl00_ContentPlaceHolder1_ddlDist")
    public  WebElement districtNameDropdown;

    @FindBy(id = "ctl00_ContentPlaceHolder1_ddlAC")
    public  WebElement assemblyConsituencyDropdown;

    @FindBy(id = "ctl00_ContentPlaceHolder1_btnlogin")
    public  WebElement getPollingStatus;

    @FindBy(id = "txtVerificationCode")
    public WebElement verificationCode;

    @FindBy(id = "btnSubmit")
    public WebElement submit;


}
