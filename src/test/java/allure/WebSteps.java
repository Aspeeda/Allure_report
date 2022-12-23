package allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    public static final String REPOSITORY = "Aspeeda/Allure_report";
    public static final String TEXT = "Welcome to issues";

    @Step("Открыть главную страницу GitHub")
    public void openMainPage() {
        open("https://github.com");
    }


    @Step("Найти через поисковую строку нужный репозиторий")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Кликнуть на репозиторий")
    public void clickRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Кликом перейти во вкладку Issue")
    public void clickIssue() {
        $("#issues-tab").click();
    }

    @Step("Проверить заголовок внутри вкладки")
    public void checkText() {
        $(withText(TEXT)).should(Condition.exist);
    }
}

