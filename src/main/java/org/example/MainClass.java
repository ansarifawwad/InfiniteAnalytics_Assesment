package org.example;
import org.example.ExcelUtilities.ExcelDownload;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class MainClass {


    String url = "https://ceotserms2.telangana.gov.in/ts_erolls/rolls.aspx";
    public static WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public void main() {
        ExcelDownload excelDownload = new ExcelDownload();

        excelDownload.downloadExcel();

        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    @AfterSuite
    public void tearDown(){
        ExcelDownload excelDownload = new ExcelDownload();
        excelDownload.deleteExcel();
        driver.quit();
    }

}