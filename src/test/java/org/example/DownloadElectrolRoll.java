package org.example;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.example.CommonUtils.Commons;
import org.example.ExcelUtilities.DataProviders;
import org.example.POM.DownloadElectrolRolls;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class DownloadElectrolRoll extends MainClass {

    @Test(dataProvider = "DownloadRecords", dataProviderClass = DataProviders.class)
    public void downloadElectrols(String district, String assemblyConstituency, String isTelugu, String poolingStation) throws InterruptedException, IOException, TesseractException {

        DownloadElectrolRolls downloadElectrolRolls = new DownloadElectrolRolls(MainClass.driver);
        Commons commons = new Commons(MainClass.driver);

        commons.click(downloadElectrolRolls.districtNameDropdown);
        MainClass.driver.findElement(By.xpath("//option[@value='" + district + "']")).click();
        commons.click(downloadElectrolRolls.assemblyConsituencyDropdown);
        Thread.sleep(3000);
        MainClass.driver.findElement(By.xpath("//option[normalize-space()='" + assemblyConstituency + "']")).click();
        commons.click(downloadElectrolRolls.getPollingStatus);
        if (isTelugu.equals("Yes")) {
            int i = Integer.parseInt(poolingStation + 1);
            MainClass.driver.findElement(By.id("ctl00_ContentPlaceHolder1_GridView1_ctl" + i + "_lnkTelugu")).click();
            Thread.sleep(5000);
            download();
        } else {
            int i = Integer.parseInt(poolingStation + 1);
            MainClass.driver.findElement(By.id("ctl00_ContentPlaceHolder1_GridView1_ctl" + i + "_lnkEnglish")).click();
            Thread.sleep(5000);
            download();
        }
    }

    public static void download() throws InterruptedException, IOException, TesseractException {

        Commons commons = new Commons(MainClass.driver);
        DownloadElectrolRolls downloadElectrolRolls = new DownloadElectrolRolls(MainClass.driver);

        String mainWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Thread.sleep(5000);
        WebElement imageElement = MainClass.driver.findElement(By.id("Image2"));
        File src = imageElement.getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"src/test/resources/CaptchImages/cropped-image.png";
        FileHandler.copy(src, new File(path));
        Thread.sleep(5000);
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("/opt/homebrew/share/tessdata"); // Path to tessdata directory
        String recognizedText = tesseract.doOCR(new File(path));
        recognizedText = recognizedText.replaceAll("\\n", "");
        commons.sendKeys(downloadElectrolRolls.verificationCode,recognizedText);
        commons.click(downloadElectrolRolls.submit);
            }
}

