package by.test;

import by.pages.LoginPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.id;

@Listeners({ScreenShooter.class})
public class LoginTest {

    @Test
    public void loginTest() {
        ScreenShooter.captureSuccessfulTests = true;
        new LoginPage()
                .open()
                .login();

        $(id("createButton")).shouldHave(text("Create new project"));
        assertThat($(id("createButton"))
                .getText())
                .isNotNull()
                .isEqualTo("Create new project");
        ElementsCollection tr = $(".table").$$("tr");
    }
}
