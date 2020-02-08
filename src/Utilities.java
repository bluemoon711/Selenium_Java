import com.Selenium_for_intellij.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utilities {
    public static Select GetSelectByElementId(String id) {
        WebElement element = TestBase.driver.findElement(By.id(id));
        return new Select(element);
    }
}
