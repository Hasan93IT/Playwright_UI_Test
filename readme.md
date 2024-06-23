# UI-Test für Web-App mit Playwright

## Einleitung
Das Ziel ist dieses Projekt zu entwickeln und auf GitHub zu committen,
außerdem wird der Test auf GitHub Action `headless` ausgeführt.
## Die realisierten Anforderungen:
####  Erstellung eine Testing Suite um alle Funktionalitäten des Shops für eine Regression zu Testen.
####  Verwendung die Programmiersprache Java.
####  Verwendung das Framework Playwright - https://playwright.dev/java/docs/intro.
####  Verwendung das Page Object Model.
####  Verwendung BDD mit Cucumber – https://cucumber.io.
####  Erstellung einen HTML Report nach dem Testlauf.
####  Erstellung ein README.
#### Alle Suites werden mit git actions ausgeführt.




##Configuration des Tests
#### in der Datei [config.properties](src/test/resources/config.properties) können Browser sowie setBrowserHeadless und Link der Web-App umgestellt werden.

## Ausführen des Tests
####1- In der Datei `Testsuite.feature`
####2- In der Klasse `RunCucumberTest`
####3- In der Konsole mit dem Befehl `mvn test` für das gesamte test oder mit`mvn test -Dgroups="Tagname"`, wobei "Tagname" in dem Fall (Login, Order,Checkout) ist.
####4- Der Test wird auch automatisch in GitHub Action nach committen ausgeführt, außerdem wird der letzte html-report global [Lin von Report in Github](https://hasan93it.github.io/Playwright_UI_Test/reports/cucumber/report.html) angezeigt  
