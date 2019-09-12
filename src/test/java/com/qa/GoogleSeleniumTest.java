package com.qa;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
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

        for(WebElement rads1 : radioSet1){
            rads1.click();
            for(WebElement rads2 : radioSet2){
                rads2.click();

            }
        }
    }

    @Test
    public void MouseMovementTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/drag-drop-range-sliders-demo.html");
        Actions action = new Actions(driver);

        List<WebElement> sliders = driver.findElements(By.name("range"));

        for(WebElement slider : sliders){
            int sliderStart = slider.getSize().getWidth() / 2;

            action.moveToElement(slider).moveByOffset(-sliderStart, 0).clickAndHold().release().perform();
            Thread.sleep(500);
            action.clickAndHold().moveByOffset(sliderStart*2, 0).release().perform();
            Thread.sleep(500);
            action.clickAndHold().moveByOffset(-sliderStart,0).perform();
            Thread.sleep(500);
        }
    }



    @Test
    public void dragAndDropTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/drag-and-drop-demo.html");
        Actions action = new Actions(driver);

        List<WebElement> objects = driver.findElement(By.id("todrag")).findElements(By.tagName("span"));
        WebElement lz = driver.findElement(By.id("mydropzone"));

        for(WebElement obj : objects){
            //action.moveToElement(obj).perform();
//            Actions act = new Actions(driver);
//            act.clickAndHold(obj);
//            act.moveToElement(lz);
//            act.release(obj);
//            act.build().perform();

            action.dragAndDrop(obj, lz).build().perform();

            //action.clickAndHold(obj).moveByOffset(20,0).release().perform();

        }

    }

    @Test
    public void dragAndDropTest2() throws InterruptedException {
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        Actions action = new Actions(driver);

        WebElement buttonOrange = driver.findElement(By.id("fourth")).findElement(By.tagName("a"));
        WebElement lz = driver.findElement(By.xpath("//*[@id=\"amt7\"]"));


        action.dragAndDrop(buttonOrange, lz).build().perform();

        Thread.sleep(600);


        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        System.out.println(scrFile.getAbsolutePath());

        Thread.sleep(10000);

//        // initialize ExtentReports with a file path
//        ExtentReports extent = new ExtentReports("Report.txt", "Report.txt");
//
//        ExtentTest test;
//
//        // initialize / start the test
//        test = extent.startTest("Verify application Title");
//
//        // add a note to the test
//        test.log(LogStatus.INFO, "Browser started");
//
//        // report the test as a pass
//        test.log(LogStatus.PASS, "verify Title of the page");
    }

    @Test
    public void dropDownTest() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"select-demo\"]"));

        for(WebElement drpdwn : dropdown.findElements(By.tagName("option"))){
            dropdown.sendKeys(Keys.DOWN);
            Thread.sleep(300);
        }

    }


    @Test
    public void bigForm() throws InterruptedException {
        driver.get("https://www.seleniumeasy.com/test/input-form-demo.html");
        Actions action = new Actions(driver);

        List<WebElement> formTextFields = driver.findElement(By.id("contact_form")).findElements(By.tagName("input"));

        int counter = 0;

        String[] answers = {"Jeff", "Jefferson", "JeffJefforeyJefferson@gmail.com", "A Phone number", "A house", "A City", "A zip code", "Acoolwebsite.com"};


        for(WebElement formTextField : formTextFields){
            if(formTextField.getAttribute("type").equals("radio")){
                formTextField.click();
            } else {
                formTextField.sendKeys(answers[counter]);
                counter++;
            }
            Thread.sleep(200);
        }

        WebElement locationDropDown = driver.findElement(By.id("contact_form")).findElement(By.tagName("select"));

        action.click(locationDropDown).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).perform();


        WebElement textArea = driver.findElement(By.id("contact_form")).findElement(By.tagName("textarea"));

        textArea.sendKeys("This is some text");
        Thread.sleep(600);

        WebElement submitBut = driver.findElement()


    }

}
