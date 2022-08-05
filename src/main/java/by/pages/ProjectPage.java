package by.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.id;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProjectPage {

    public ProjectPage open(){
        Selenide.open("/projects");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        $((WebElement) id("createButton")).shouldBe(visible);
        return new ProjectPage();
    }
}
