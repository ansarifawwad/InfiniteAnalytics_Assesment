package org.example.CommonUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Commons {

    WebDriver driver;


    public Commons(WebDriver driver) {
        this.driver = driver;
    }


    public void sendKeys(WebElement webElement,String input){
        webElement.sendKeys(input);
    }

    public void click(WebElement webElement){
        webElement.click();
    }

    public void elementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

}