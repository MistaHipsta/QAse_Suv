package by.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("/login");
        getWebDriver()
                .manage()
                .window()
                .maximize();
        return new LoginPage();
    }

    public ProjectPage login(){
        $(id("inputEmail")).sendKeys("suvorov.evgenii2727@gmail.com");
        $(id("inputPassword")).sendKeys(".#mYZV.v4*iV6zH");
        $(id("btnLogin")).click();
        return new ProjectPage();
    }
}
