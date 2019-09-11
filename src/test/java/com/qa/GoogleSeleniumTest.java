package com.qa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GoogleSeleniumTest {
    private ChromeDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\IdeaProjects\\seleniumtesting\\src\\test\\java\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    //fake-input
    @Test
    public void searchTest() throws InterruptedException {
        driver.get("http://google.com");
        Thread.sleep(2000);
        WebElement searchField = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));
        assertTrue(searchField.isDisplayed());
        searchField.sendKeys("funny cat pictures");
        Thread.sleep(2000);
        searchButton.click();
        Thread.sleep(2000);
        WebElement linkToBiggerPicture = driver.findElementByLinkText("Images for funny cat");
        linkToBiggerPicture.click();
        Thread.sleep(2000);
        WebElement catLink = driver.findElement(By.xpath("//*[@id=\"Kh4XAWzj-XKpjM:\"]"));
        Thread.sleep(2000);
        catLink.click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0, 2000)");
        Thread.sleep(2000);

    }

    @Test
    public void inputFieldTest() throws InterruptedException {
        //input fields
        driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        WebElement userMessage = driver.findElement(By.id("user-message"));
        userMessage.sendKeys("Test");
        WebElement messageButton = driver.findElement(By.id("get-input")).findElement(By.tagName("button"));
        messageButton.click();
        Thread.sleep(2000);

        //Two input fields
        WebElement sumBox1 = driver.findElement(By.id("sum1"));
        WebElement sumBox2 = driver.findElement(By.id("sum2"));
        WebElement totalButton = driver.findElement(By.id("gettotal")).findElement(By.tagName("button"));

        sumBox1.sendKeys("4");
        sumBox2.sendKeys("5");
        totalButton.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0, 2000)");
        Thread.sleep(2000);
    }

    @Test
    public void checkBoxTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebElement checkBox = driver.findElement(By.id("isAgeSelected"));
        WebElement textAge = driver.findElement(By.id("txtAge"));

        checkBox.click();
        Thread.sleep(1000);
        assertTrue(textAge.isDisplayed());
        checkBox.click(); //The page is broken

        //Checkbox test2
        List<WebElement> checkBoxes = driver.findElementsByClassName("cb1-element");
        WebElement uncheckButton = driver.findElement(By.id("check1"));

        for(WebElement cB : checkBoxes){
            cB.click();
            Thread.sleep(500);
        }

        Thread.sleep(100);
        assertEquals(uncheckButton.getAttribute("value"), "Uncheck All");


        uncheckButton.click();
        Thread.sleep(100);
    }


    @Test
    public void radioButtonTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        List<WebElement> radioBs1 = driver.findElements(By.name("optradio"));
        WebElement buttonCheck = driver.findElement(By.id("buttoncheck"));
        WebElement radioBText = driver.findElement(By.className("radiobutton"));

        for(WebElement radio : radioBs1){
            radio.click();
            buttonCheck.click();
            assertEquals(radioBText.getText(), "Radio button '"+radio.getAttribute("value")+"' is checked");
        }

        //Group Radio buttons
        List<WebElement> radioSet1 = driver.findElements(By.name("gender"));
        List<WebElement> radioSet2 = driver.findElements(By.name("ageGroup"));
        WebElement

        for(WebElement rads1 : radioSet1){
            rads1.click();
            for(WebElement rads2 : radioSet2){
                rads2.click();

            }
        }



    }

}
