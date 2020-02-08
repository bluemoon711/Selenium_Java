import com.Selenium_for_intellij.Constants;
import com.Selenium_for_intellij.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class DemoTest {
    @Before
    public void startBrowser() {
        TestBase.initialize();
    }

    @Test
    public void demo() {
        WebDriverWait wait = new WebDriverWait(TestBase.driver, 4);
        String title = TestBase.driver.getTitle();
        Assert.assertEquals(Constants.homePageTitle, title);
        String tagName = " ";
        tagName = TestBase.driver.findElement(By.cssSelector("#nav-search-dropdown-card > div > div")).getText();
        Assert.assertEquals("All", tagName);

        //Dropdown
        Utilities.GetSelectByElementId("searchDropdownBox").selectByVisibleText("Books");
        //Typing the text
        WebElement myElement = TestBase.driver.findElement(By.id("twotabsearchtextbox"));
        myElement.sendKeys("Game of Thrones");
        TestBase.driver.findElement(By.className("nav-input")).click();

        //Select the book
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Dragons")));
        WebElement book =  TestBase.driver.findElement(By.partialLinkText("Dragons"));
        System.out.println("Found books: " + book.getText());

        book.click();
        String numBookSets = "2";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        Utilities.GetSelectByElementId("quantity").selectByVisibleText(numBookSets);
        TestBase.driver.findElement(By.cssSelector("#add-to-cart-button")).click();
        System.out.println("Added " + numBookSets + " book sets to the cart");

        //Proceed to checkout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlb-view-cart-announce")));
        WebElement cartBtn = TestBase.driver.findElement(By.id("hlb-view-cart-announce"));
        WebElement checkoutBtn = TestBase.driver.findElement(By.id("hlb-ptc-btn-native"));
        Assert.assertEquals(true, cartBtn.isDisplayed() );
        Assert.assertEquals(true, checkoutBtn.isDisplayed() );

        String checkoutText = checkoutBtn.getText();
        System.out.println(checkoutText);
        assertThat(checkoutText, containsString("2 items"));
        checkoutBtn.click();

        //Redirect to sign in page
        String aTitle = "" ;
        aTitle = TestBase.driver.getTitle();
        Assert.assertEquals(Constants.signInPageTitle, aTitle);
    }

    @After
    public void tearDown() {
        TestBase.quit();
    }
}
