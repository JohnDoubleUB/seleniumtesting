package com.qa;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSeleniumTest {
    private ChromeDriver driver;

    @Before
    public void setup(){
        driver = new ChromeDriver();
    }

}
