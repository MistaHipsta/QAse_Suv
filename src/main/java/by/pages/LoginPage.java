package by.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

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

    public LoginPage logOut(){
        $(xpath("//img[@class='X8BNLp']")).click();
        $(xpath("//span[normalize-space()='Sign out']")).click();
        return new LoginPage();
    }
}
