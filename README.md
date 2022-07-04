# ITooLabs. Проект автотестов 
<a href="https://itoolabs.com/"><img alt="ITooLabs" height="50" src="external/logo.png"/></a>
___
___
### Содержание

___

### Cтек технологий

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="Intelij IDEA" height="50" src="external/technologies/Intelij_IDEA.svg" width="50"/></a>
<a href="https://www.java.com/"><img alt="Java" height="50" src="external/technologies/Java.svg" width="50"/></a>
<a href="https://junit.org/junit5/"><img alt="JUnit5" height="50" src="external/technologies/JUnit5.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="external/technologies/Selenide.svg" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="external/technologies/Selenoid.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="external/technologies/Gradle.svg" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="external/technologies/Jenkins.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure" height="50" src="external/technologies/Allure.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="external/technologies/GitHub.svg" width="50"/></a>
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="external/technologies/Telegram.svg" width="50"/></a>
</div>

___

### Объекты тестирования
- **_Главная страница сайта_** (ссылка на Github)

Разработаны автотесты проверок таких элементов как: 
ссылка на главную страницу в лого
элементы выбора текущей локали
элементы списка основных услуг в хэдере
различные ссылки на форму запроса Demo-версии продукта
поля формы (позитивный сценарий)
кнопки прокрутки ленты новостей.

- **_Форма запроса Demo-версии продукта_** (ссылка на Github)

Разработаны автотесты с негативными сценариями для проверок: 
отдельных полей формы
возможности отправки формы на сервер с невалидными данными.

Одной из особенностей разработанных автотестов является использование валидных данных для заполнения полей формы, генерируемых в случайном порядке для каждого повтора теста, что минимизирует влияние "эффекта пестицида" на качество тестов.
___
### Команды запуска тестов
<gradle clean>
Команды запуска тестов из терминала с пояснением ключей
  
### Скриншоты
#### Java, JUnit5, Selenide
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
  
#### Selenoid
<video src="https://user-images.githubusercontent.com/72714071/177209634-f8b6ae6c-90ca-4b47-84b3-8199a3347a36.mp4" controls="controls" style="max-width: 730px;" poster="https://github.com/grad0ff/Itoolabs/blob/master/external/technologies/Selenoid.svg">
Видео не доступно для этого браузера
</video>
  
#### Gradle

#### Jenkins

#### Allure

#### GitHub

#### Telegram



- Иконки используемого стека для красоты
- Сделайте джобу в Jenkins, добавьте Allure отчёт, уведомления в чат Telegram, Selenoid.
- Оформите всё со скриншотами в readme.md
