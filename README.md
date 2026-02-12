ReportPortal Launches Autotests

Автоматизированные UI и API тесты для раздела Launches сайта
https://demo.reportportal.io/ui/

Цель

Реализовать тестовый фреймворк с разделением на слои

Использовать паттерн Page Object Model (POM)

Реализовать:

4 UI автотеста

6 API автотестов

Настроить:

логирование

параллельный запуск тестов

CI/CD с разделением UI и API тестов

генерацию Allure Report

Технологии

Java 17, Maven, JUnit 5, Playwright, RestAssured, SLF4J, Logback, AssertJ, Allure, GitHub Actions

🖥 UI тесты

Инструмент: Playwright

Подход: POM

Раздел: Launches

Количество: 4

Учетные данные:
Login: default
Password: 1q2w3e

🌐 API тесты

Инструмент: RestAssured

Аутентификация: API Key (Bearer Token)

Раздел: Launches

Количество: 6

Покрыты основные CRUD операции:
GET / POST / PUT / DELETE

Для запуска API тестов требуется переменная среды:

API_KEY

🧪 Параллельный запуск

Параллельное выполнение тестов включено с помощью JUnit 5
(junit-platform.properties).

📝 Логирование

Используется SLF4J + Logback.
Логируются шаги тестов и HTTP-запросы API
(logback-test.xml).

⚙ CI/CD

Настроен GitHub Actions:

Раздельный запуск UI и API тестов

Использование GitHub Secrets для API_KEY

Генерация Allure отчёта

Автоматический деплой отчёта на GitHub Pages

📊 Allure Report

Отчёт доступен по ссылке:

https://olgakharina.github.io/Report_portal_tests/

▶️ Запуск тестов
Все тесты
mvn clean test

Только UI
mvn clean test -Dgroups=UI

Только API
$env:API_KEY="your_api_key"
mvn clean test -Dgroups=API