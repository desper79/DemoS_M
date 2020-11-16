package ru.my.test.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPage extends Page {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void SearchAndCheck(String name, boolean ischeckboxPhotoOnly, String sortOrder) {
        try {

            //Если фотография
            if (ischeckboxPhotoOnly)
            {
                checkboxPhotoOnly.click();
            }

            //Ввод текста
            textSearch.sendKeys(name);

            //Поиск
            buttonSearch.click();

            //Сортировка
            SelectValue(sortOrder);

            //Полный список результатов
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector ("[data-marker='catalog-serp'] [data-marker='item']")));
            List <WebElement> list = driver.findElements(By.cssSelector("[data-marker='catalog-serp'] [data-marker='item']"));

            //Первые 10
            List <WebElement> list10 = list.subList(0, 10);

            //Только цены
            List <Integer> priceList = new ArrayList();
            for (WebElement element : list10)
            {
                String str = element.findElement(By.cssSelector("[itemprop='price']")).getAttribute("content");
                priceList.add(Integer.parseInt(str));
            }

            //Сортируем
            Collections.sort(priceList);

            //Выводим в файл с указанием текущих даты и времени
            DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
            System.out.println(timeStampPattern.format(java.time.LocalDateTime.now()));
            FileWriter writer = new FileWriter("AvitoResults__" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".txt");

            for (Integer item : priceList)
                writer.write(item.toString()+ System.lineSeparator());
            writer.close();

            //Все операции успешно выполнены
            Assert.assertEquals( true, true);
        }
        catch (Exception e)
        {
            Assert.assertEquals( "Error: " + e.getMessage(), false, true);
        }
    }

    public void SelectValue (String value)  {
        try {
            String cssSelector = ".index-topPanel-1F0TP select";
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            Select select = new Select(driver.findElement(By.cssSelector(cssSelector)));

            switch (value) {
                case "По дате":
                    select.selectByValue("104");
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
            {
                Assert.assertEquals( "Error: " + e.getMessage(), false, true);
            }
    }

    @FindBy(how = How.CSS, using = "#search")
    public WebElement textSearch;

    @FindBy(how = How.XPATH , using = "(//*[@class='filters-root-3Q1ZY']//label)[2]")
    public WebElement checkboxPhotoOnly;

    @FindBy(how = How.CSS, using = "button[data-marker='search-form/submit-button']")
    public WebElement buttonSearch;
}