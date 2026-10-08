# Android Calculator

![Kotlin](https://img.shields.io/badge/Kotlin-2.4.10-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-Design-blue)
![mXparser](https://img.shields.io/badge/mXparser-Math%20Engine-brightgreen)
![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange)

Калькулятор с современным дизайном, мгновенным вычислением выражений и поддержкой математических операций.

Приложение разработано на **Kotlin** и **Jetpack Compose** с применением архитектурного паттерна **MVI / UDF** (Unidirectional Data Flow). Логика построена вокруг единого состояния экрана и командного взаимодействия через `ViewModel`, а вычисление выражений выполняется с помощью математического движка **mXparser**.

## Скриншоты
| Главный экран | Расчёт выражения | Ошибка вычисления | Тёмная тема |
| :---: | :---: | :---: | :---: |
| <img width="317" height="660" alt="image" src="https://github.com/user-attachments/assets/38544dae-6035-4d51-ab63-9e049db93aff" /> | <img width="317" height="657" alt="image" src="https://github.com/user-attachments/assets/cd6be409-02a7-46dc-889d-51e06f4b96af" /> | <img width="318" height="660" alt="image" src="https://github.com/user-attachments/assets/5002cc07-3080-4710-a6af-a837d7903a90" /> | <img width="316" height="657" alt="image" src="https://github.com/user-attachments/assets/d2e20837-fd4c-4be6-8d07-c317aaad44c2" />


## Основные возможности

| Возможность | Описание |
| :--- | :--- |
| **Мгновенный предпросмотр** | Динамический расчёт результата выражения в реальном времени по мере ввода |
| **Интеллектуальные скобки** | Автоматическое определение открывающих и закрывающих скобок на основе контекста |
| **Обработка ошибок** | Безопасная валидация выражений и отображение ошибок при некорректном синтаксисе |
| **Современный UI/UX** | Верстка на Jetpack Compose с палитрой Material 3 и плавной прокруткой истории ввода. Поддержка тёмной и светлой тем |

## Технологический стек

- **Язык:** Kotlin
- **UI:** Jetpack Compose, Material 3
- **Архитектура:** MVI / UDF (StateFlow, Sealed Interfaces для состояний и команд)
- **Математический движок:** mXparser (парсинг и вычисление строковых выражений)

## Архитектурный подход

В проекте реализован подход однонаправленного потока данных (**Unidirectional Data Flow**):

- **CalculatorCommand:** события пользовательского ввода (`Input`, `Evaluate`, `Clear`, `Delete`).
- **CalculatorViewModel:** бизнес-логика обработки команд, управление строкой выражения и расчёт через математический парсер.
- **CalculatorState:** строго типизированное состояние UI (`Initial`, `Input`, `Success`, `Error`), транслируемое во view через `StateFlow`.

## Сборка и запуск

1. Откройте Android Studio.
2. Клонируйте репозиторий:
```bash
git clone https://github.com/ermakown/AndroidCalculator.git
```
3. Запустите приложение на эмуляторе или физическом устройстве с Android 7.0 (API 24) и выше.
