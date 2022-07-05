# ITooLabs. Автотесты на Java
<a href="https://itoolabs.com/"><img alt="ITooLabs" height="50" src="external/logo.png"/></a>

## Содержание :bookmark_tabs:

<a href="#stack">1. Cтек технологий :hammer_and_wrench:</a>

<a href="#object">2. Объекты тестирования :mag:</a>

<a href="#console">3. Команды запуска тестов :computer:</a>

<a href="#screenshot">4. Скриншоты :camera_flash:</a>

<a href="#ijjs">
  - InteliJ IDEA <img alt="InteliJ IDEA" src="external/technologies/Intelij_IDEA.svg" height="20" width="20"/>
  Java<img alt="Java" src="external/technologies/Java.svg" height="20" width="20"/>
  JUnit 5 <img alt="JUnit 5" src="external/technologies/JUnit5.svg" height="20" width="20"/>
  Selenide <img alt="Selenide" src="external/technologies/Selenide.svg" height="20" width="20"/>
</a>
<a href="#selenoid">- Selenoid <img alt="Selenoid" src="external/technologies/Selenoid.svg" height="20" width="20"/></a>
<a href="#gradle">- Gradle <img alt="Gradle" src="external/technologies/Gradle.svg" height="20" width="20"/></a>
<a href="#jenkins">- Jenkins <img alt="Jenkins" src="external/technologies/Jenkins.svg" height="20" width="20"/></a>
<a href="#allure">- Allure <img alt="Allure" src="external/technologies/Allure.svg" height="20" width="20"/></a>
<a href="#telegram">- Telegram <img alt="Telegram" src="external/technologies/Telegram.svg" height="20" width="20"/></a>

<a id="stack"></a>
## Cтек технологий :hammer_and_wrench:
<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="external/technologies/Intelij_IDEA.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="external/technologies/Java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="external/technologies/JUnit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="external/technologies/Selenide.svg" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="external/technologies/Selenoid.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="external/technologies/Gradle.svg" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="external/technologies/Jenkins.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="external/technologies/Allure.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="external/technologies/GitHub.svg" width="50"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="external/technologies/Telegram.svg" width="50"/></a>
</div>

<a id="object"></a>
## Объекты тестирования :mag:
- **_Главная страница сайта_**

Разработаны автотесты для проверок таких элементов как:
:white_check_mark: ссылка на главную страницу в лого
:white_check_mark: элементы выбора текущей локали
:white_check_mark: элементы списка основных услуг в хэдере
:white_check_mark: ссылки на форму запроса Demo-версии продукта
:white_check_mark: поля формы (позитивный сценарий)
:white_check_mark: кнопки прокрутки ленты новостей.

- **_Форма запроса Demo-версии продукта_**

Разработаны автотесты с негативными сценариями для проверок:
:white_check_mark: каждого отдельного поля формы
:white_check_mark: возможности отправки формы на сервер с невалидными данными.

> _Одной из особенностей разработанных автотестов является использование валидных данных для заполнения полей формы,
> генерируемых псевдослучайно для каждого повтора теста, что минимизирует влияние "эффекта пестицида" на
> качество тестирования_

<a id="console"></a>
## Команды запуска тестов :computer:
```bash
gradle clean 
${TESTNAME}
-Dbrowser=${BROWSER}
-DwindowSize=${WINDOWSIZE} 
-DremoteWebDriver=${REMOTEDRIVER}
```
> `${TESTNAME}` - имя задачи из [*build.gradle*](build.gradle) [  *test* <sub>(default)</sub> , *indexPageTest* , *demoFormTest* ]
>
> `${BROWSER}` - браузер [ *firefox* , *chrome* <sub>(default)</sub> ]
>
> `${WINDOWSIZE}` - размер окна браузера [ *1024x768* , *1366x768* <sub>(default)</sub> , *1366x768* ]
>
> `${REMOTEDRIVER}` - подключение удаленного браузера для тестов [ *false* , *true* <sub>(default)</sub> ]

<a id="screenshot"></a>
## Скриншоты :camera_flash:
<a id="ijjs"></a>
#### <a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="external/technologies/Intelij_IDEA.svg" width="50"/>InteliJ IDEA</a><a href="https://www.java.com/"><img alt="Java" height="50" src="external/technologies/Java.svg" width="50"/>Java</a><a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="external/technologies/JUnit5.svg" width="50"/>JUnit 5</a><a href="https://selenide.org/"><img alt="Selenide" height="50" src="external/technologies/Selenide.svg" width="50"/>Selenide</a>
> _для оформления кода автотестов_

```java
@Tag("index_page_positive_test")
@Owner("a.gradov")
@Link(name = "itoolabs.com", url = "https://itoolabs.com/")
@Feature("Работа с главной страницей")
@DisplayName("Примеры тестов главной страницы сайта ITooLabs")
public class ExampleTest extends TestBase {
    IndexPage indexPage = new IndexPage();
    Faker faker = new Faker();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Пользователь заполняет форму для получения Demo-версии")
    @Description("Проверяется корректность заполнения всех полей формы запроса Demo-версии продукта")
    @DisplayName("Тест всех полей формы")
    void demoFormFieldsTest() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String company = faker.company().name();
        String comment = faker.chuckNorris().fact();
        Allure.parameter("Имя", name);
        Allure.parameter("E-mail", email);
        Allure.parameter("Телефон", phone);
        Allure.parameter("Компания", company);
        Allure.parameter("Комментарий", comment);
        step("Нажимаем на демолайне на кнопку получения Demo-версии ",
                () -> $(".demoBuyButton.first .buyDemo").click());
        indexPage.demoForm
                .setName("", name)
                .setEmail("", email)
                .setPhone("", phone)
                .setCompany("", company)
                .setComments("", comment);
        step("Проверяем что кнопка отправки формы активна",
                () -> indexPage.demoForm.submitButton.hover().shouldBe(enabled));
        step("Проверяем что нет сообщения о необходимости заполнения обязательный полей",
                () -> indexPage.demoForm.requiredFieldsMessage.shouldNotBe(visible));
    }

    @ParameterizedTest
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("requiredFieldDataStream")
    @Story("Пользователь пытается отправить форму с невалидными данными")
    @Description("Проверяется невозможность отправки формы с невалидными данными в полях Имя, E-mail, Компания")
    @DisplayName("Тест отправки формы: ")
    void sendInvalidFormTest(String name, String email, String company) {
        openDemoForm();
        String nameValue = name.equals("ПРОБЕЛЫ") ? "   " : name;
        String emailValue = email.equals("ПРОБЕЛЫ") ? "   " : email;
        String companyValue = company.equals("ПРОБЕЛЫ") ? "   " : company;
        indexPage.demoForm
                .setName(name, nameValue)
                .setEmail(email, emailValue)
                .setCompany(company, companyValue);
        step("Проверяем что кнопка Отключить неактивна",
                () -> indexPage.demoForm.submitButton.hover().shouldBe(disabled));
    }

    static Stream<Arguments> requiredFieldDataStream() {
        return Stream.of(
                Arguments.of("ПРОБЕЛЫ", faker.internet().emailAddress(), faker.company().name()),
                Arguments.of(faker.name().firstName(), "ПРОБЕЛЫ", faker.company().name()),
                Arguments.of(faker.name().firstName(), faker.internet().emailAddress(), "ПРОБЕЛЫ"));
    }
}
```

<a id="selenoid"></a>
#### <a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="external/technologies/Selenoid.svg" width="50"/>Selenoid</a>
> _для прогона нагруженных автотестов на удаленном сервере_

<video src="https://user-images.githubusercontent.com/72714071/177209634-f8b6ae6c-90ca-4b47-84b3-8199a3347a36.mp4"
controls="controls" style="max-width: 730px;" poster="https://github.com/grad0ff/Itoolabs/blob/master/external/technologies/Selenoid.svg">
Видео не доступно для этого браузера
</video>

<a id="gradle"></a>
#### <a href="https://gradle.org/"><img alt="Gradle" height="50" src="external/technologies/Gradle.svg" width="50"/>Gradle</a>
> _для автоматической сборки и управления проектом на локальном ПК_

```groovy
plugins {
    id 'java'
    id 'io.qameta.allure' version '2.10.0'
}

repositories {
    mavenCentral()
}

allure {
    report {
        version.set('2.18.1')
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set('2.18.1')
            }
        }
    }
}

dependencies {
    testImplementation(
            'org.junit.jupiter:junit-jupiter:5.8.2',
            'com.codeborne:selenide:6.6.5',
            'com.github.javafaker:javafaker:1.0.2',
            'org.assertj:assertj-core:3.23.1',
            'io.qameta.allure:allure-selenide:2.18.1',
            'org.slf4j:slf4j-simple:1.7.36')
}

task indexPageTest(type: Test) {
    useJUnitPlatform {
        includeTags "index_page_positive_test"
    }
}

task demoFormTest(type: Test) {
    useJUnitPlatform {
        includeTags "demo_form_negative_test"
    }
}
```

<a id="jenkins"></a>
####  <a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="external/technologies/Jenkins.svg" width="50"/>Jenkins</a>
> _для решения последовательных задач по сборке проекта, прогону автотестов, получению отчетов и отправке уведомлений по результатам сборки_

<a id="allure"></a>
#### <a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="external/technologies/Allure.svg" width="50"/>Allure</a>
> _для формирования отчетов по результам проведенных автотестов_

<a href="https://jenkins.autotests.cloud/job/013-grad0ff-14-itoolabs/1/allure/">
<img src="https://user-images.githubusercontent.com/72714071/177305487-395e6be8-7ed4-43ff-ae36-765279f3caa2.png">
</a>

> Из за особенности работы с Selenoid, заключающейся в получении только одного видеопотока на всю сессию (т.е. тестран), реализовано добавление в отчете в название видеофайла  определенной метки времени. Она соответствует началу каждого проваленного теста, что значительно облегчает навигацию при просмотре.

<a id="telegram"></a>
#### <a href="https://telegram.org/"><img alt="Telegram" height="50" src="external/technologies/Telegram.svg" width="50"/>Telegram</a>
> _для оперативного получения уведомлений о результатах прогона тестов_
# скриншот чата
