package ru.my.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OpenPage extends Page {

    public OpenPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(ConfProperties.getProperty("homepage"));
        driver.manage().window().maximize();
    }
}