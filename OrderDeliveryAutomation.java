package com.vivek;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OrderDeliveryAutomation {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "/home/vivek/Downloads/chromedriver-linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        String[] vegetablesToSelect = { "Cucumber", "Beetroot", "Tomato", "Carrot" };

        // Call the method to select items
        selectItems(driver, vegetablesToSelect);

        // Call the method to proceed to the cart
        proceedToCart(driver);
    }

    // Select items based on the given array of vegetables
    public static void selectItems(WebDriver driver, String[] vegetablesToSelect) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        int loopFlag = 0;

        for (int i = 0; i < products.size(); i++) {
            String[] productName = products.get(i).getText().split("-");
            String pureProductName = productName[0].trim();
            List<String> inputVegetablesList = Arrays.asList(vegetablesToSelect);

            if (inputVegetablesList.contains(pureProductName)) {
                int count = 1;
                loopFlag++;
                while (count < 5) {
                    driver.findElements(By.cssSelector(".increment")).get(i).click();
                    count++;
                }
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

                // Break the loop if all required vegetables are selected
                if (loopFlag == inputVegetablesList.size()) {
                    break;
                }
            }
        }
    }

    // Proceed to the cart and apply promo code
    public static void proceedToCart(WebDriver driver) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5)); // ! Explicit declartion
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
        driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector("button.promoBtn")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));// ! Explicit
                                                                                                       // using
        System.out.println(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText());
    }
}
