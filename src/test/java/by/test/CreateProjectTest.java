package by.test;

import by.pages.LoginPage;
import by.pages.ProjectPage;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenShooter.class})
public class CreateProjectTest {
    @Test
    public void createNewProject() {
        ScreenShooter.captureSuccessfulTests = true;
        new LoginPage()
                .open()
                .login();
        new ProjectPage()
                .openProjectPage()
                .createValidProject("Test name", "AUTOTST", "some text")
                .deleteCreatedProject();
        new LoginPage()
                .logOut();
    }
}

