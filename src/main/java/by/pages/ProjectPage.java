package by.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class ProjectPage {

    public ProjectPage openProjectPage() {
        Selenide.open("/projects");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        $(id("createButton")).shouldBe(visible);
        return new ProjectPage();
    }

    public ProjectPage createValidProject(String projectName, String projectCode, String description) {
        $(id("createButton")).click();
        $(id("inputTitle")).sendKeys(projectName);
        $(id("inputCode")).sendKeys(projectCode);
        $(id("inputDescription")).sendKeys(description);
        $(id("public-access-type")).click();
        $(xpath("//button[@type='submit']")).click();
        return new ProjectPage();
    }

    public ProjectPage deleteCreatedProject (){
        openProjectPage();
        $(xpath("//i[@class='fa fa-ellipsis-h']")).click();
        $(xpath("//a[@class='text-danger']")).click();
        $(xpath("//button[@type='submit']")).click();
        return new ProjectPage();
    }
}
