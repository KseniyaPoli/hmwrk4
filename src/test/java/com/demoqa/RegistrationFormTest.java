package com.demoqa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1800x1028";
    }

    @Test
    void fillForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ksenia");
        $("#lastName").setValue("P");
        $("#userEmail").setValue("k@mail.ru");
        $(byText("Female")).click();
        $("#userNumber").setValue("1236547890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(3);
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--029").click();
        $("#subjectsInput").setValue("Economics").pressEnter();
        $("[for = hobbies-checkbox-1]").click();
        $("[for = hobbies-checkbox-3]").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/1st.jpg"));
        $("#currentAddress").setValue("Obninsk");
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Ksenia P"),
                text("k@mail.ru"),
                text("Female"),
                text("1236547890"),
                text("29 April,1990"),
                text("Economics"),
                text("Sports, Music"),
                text("1st.jpg"),
                text("Obninsk"),
                text("Haryana Panipat"));

    }

}
