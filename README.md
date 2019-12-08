# qa-battle
 - Web UI тесты для проверки сайта lodalhost:8080
### Тесты написаны с использованием следующих технологий: 
  - Java
  - Junit5
  - [Selenide](http://selenide.org)  
  - [Allure2](https://github.com/allure-framework/allure2)
  - gradle
# Сборка 
 - Для сборки используется Gradle
 `$./gradlew clean compileJava compileTestJava`
 
# Запуск тестов:
> Всех тестов
 - `$./gradlew clean test`
> Выборочных тестов
 - `$./gradlew clean test --tests=<nameTestClass.nameTest>` 
 
# Создание отчета
- Создать отчет 
`$./gradlew allureReport`
- Создание и просмотр отчета в браузере
`$./gradlew allureServe` 

