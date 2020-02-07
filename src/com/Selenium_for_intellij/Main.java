package com.Selenium_for_intellij;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Main {

    public static void main(String[] args) throws  InterruptedException{
        TestBase.initialize();
        WebDriverWait wait = new WebDriverWait(TestBase.driver, 4);
        String title = TestBase.driver.getTitle();
        System.out.println("page title is: " + title);
        if(title.equalsIgnoreCase(Constants.homePageTitle)) {
            System.out.println("Passed : Title matches");
        } else {
            System.out.println(title);
        }
        //locate a web element
        String tagname = " ";
        tagname = TestBase.driver.findElement(By.cssSelector("#nav-search-dropdown-card > div > div")).getText();
        System.out.println(tagname);
        //dropdown
        Select drpSearch = new Select(TestBase.driver.findElement(By.id("searchDropdownBox")));
        drpSearch.selectByVisibleText("Books");
        //Typing the text
        WebElement myElement = TestBase.driver.findElement(By.id("twotabsearchtextbox"));
        myElement.sendKeys("Game of Thrones");
        TestBase.driver.findElement(By.className("nav-input")).click();
        //Select the book
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Dragons")));
        WebElement book =  TestBase.driver.findElement(By.partialLinkText("Dragons"));
        System.out.println(book.getText());
        book.click();
        //add two
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        Select drpQty = new Select(TestBase.driver.findElement(By.name("quantity")));
        drpQty.selectByVisibleText("2");
        //add to cart
        TestBase.driver.findElement(By.cssSelector("#add-to-cart-button")).click();
        //proceed to checkout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlb-view-cart-announce")));
        WebElement cartBtn = TestBase.driver.findElement(By.id("hlb-view-cart-announce"));
        WebElement checkoutBtn = TestBase.driver.findElement(By.id("hlb-ptc-btn-native"));
        if(cartBtn.isDisplayed()){
            System.out.println("Cart button is Visible");
        }else{
            System.out.println("Cart button is InVisible");
        }
        if( checkoutBtn.isDisplayed()){
            System.out.println("Checkout button is Visible");
        }else{
            System.out.println("Checkout button is InVisible");
        }
        String checkoutText = checkoutBtn.getText();
        System.out.println(checkoutText);
        if(checkoutText.contains("2 items")) {
            System.out.println("Expected text is obtained");
        }else{
            System.out.println("Expected text is not obtained");
        }
        checkoutBtn.click();
        String aTitle = "" ;
        aTitle = TestBase.driver.getTitle();
        if (aTitle.equals(Constants.signInPageTitle))
        {
            System.out.println( "Test Passed : Sign in page") ;
        }
        else {
            System.out.println( "Test Failed" );
        }
        TestBase.quit();
    }
}
