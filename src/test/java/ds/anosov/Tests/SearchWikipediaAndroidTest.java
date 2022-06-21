package ds.anosov.Tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchWikipediaAndroidTest extends TestBase {

    @Test
    @DisplayName("Поиск статьи в Википедии")
    void searchTest() {
        step("Поиск статьи", () -> {
            back();
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Blizzard Entertainment");
        });

        step("Удостовериться, что найдены варианты", () -> {
            $$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        });

        step("Переход к статье", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
            $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Blizzard Entertainment"));
        });
    }
}