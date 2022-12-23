package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Epic("QA_GURU")
@Feature("Allure")
@Story("Check Issue in repo")
@Owner("Darya M.")
public class TestLambdaStep extends WebSteps {

    @Test
    @DisplayName("Check Issue (dynamic steps)")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("1.Открыть главную страницу GitHub", () -> open("https://github.com"));

        step("2.Найти через поисковую строку нужный репозиторий", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("3. Кликнуть на репозиторий", () -> $(linkText(REPOSITORY)).click());

        step("4. Кликом перейти во вкладку Issue", () -> $("#issues-tab").click());

        step("Проверить заголовок внутри вкладки", () -> {
            $(withText(TEXT)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Check Issue (annotated steps)")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickRepository(REPOSITORY);
        steps.clickIssue();
        steps.checkText();
    }
}
