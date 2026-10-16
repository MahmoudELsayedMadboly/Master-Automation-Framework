# Master Automation Framework

A reusable, layered test automation framework in **Java** for **Web UI** and **REST API** testing, built on **Selenium WebDriver**, **REST Assured**, and **TestNG**.

The framework is packaged as a **Maven library** and kept separate from the tests. Each test project adds it as a dependency, writes its own Page Objects, API services, and test cases, and gets driver management, waits, browser utilities, API setup, logging, configuration, and failure screenshots from the framework.

---

## Key Features

- **One framework for UI and API testing**, with a shared core for configuration, logging, and reporting
- **Framework and tests decoupled**: installed as a Maven artifact and consumed by separate test projects
- **Thread-safe WebDriver management** using `ThreadLocal`, ready for parallel execution
- **Cross-browser support**: Chrome, Firefox, Edge, and Safari, with automatic driver setup via WebDriverManager
- **Page Object Model base class** (`BasePage`) with built-in waits and logging for clicks, typing, dropdowns, text, scrolling, and hovering
- **Explicit waits only**: centralized in `WaitUtils` (visibility, clickability, presence, text, URL, alerts)
- **Browser utilities**: JavaScript executor, alerts, windows and tabs, frames, mouse actions, and screenshots
- **REST Assured base layer** (`BaseApi`) with shared request/response specifications, logging, and Bearer token support
- **API helpers** (`ApiUtils`) for status checks, JSON path extraction, POJO deserialization, and response-time checks
- **Externalized configuration** in `.properties` files, overridable from the command line for CI/CD (`-Dkey=value`)
- **Centralized logging** with Log4j2: console output plus rolling log files
- **Automatic screenshot on failure** and a pass/fail/skip summary through a TestNG listener
- **Pluggable reporting**: an `IReporter` interface lets each project plug in Extent Reports, Allure, or any other reporter without changing the framework
- **Smoke and regression suites** organized with TestNG groups

---

## Architecture

The framework is split into four layers. Each layer depends only on the layers below it.

```
┌──────────────────────────────────────────────────────────┐
│  Test Layer        BaseTest · TestListener · TestNG suites │
├───────────────────────────────┬──────────────────────────┤
│  UI Layer (Selenium)          │  API Layer (REST Assured)│
│  BasePage · WaitUtils         │  BaseApi · ApiUtils      │
│  BrowserUtils                 │  services/ · models/     │
├───────────────────────────────┴──────────────────────────┤
│  Core Layer   DriverFactory · DriverManager · ConfigManager│
│               LogManager · IReporter                       │
└──────────────────────────────────────────────────────────┘
```

| Layer | Package | Responsibility |
|---|---|---|
| **Core** | `com.framework.core` | Driver creation and thread-safe storage, configuration, logging, reporting contract |
| **UI** | `com.framework.ui` | Base Page Object, explicit waits, browser utilities |
| **API** | `com.framework.api` | REST Assured specifications, response helpers |
| **Test** | `com.framework.ui.base`, `com.framework.listeners` | Browser lifecycle per test, logging and screenshots on failure |

An interactive architecture diagram is included in [`framework-architecture.html`](framework-architecture.html).

---

## Project Structure

```
master-framework/
├── pom.xml
├── framework-architecture.html          # Visual architecture overview
└── src/
    ├── main/java/com/framework/
    │   ├── core/
    │   │   ├── config/ConfigManager.java      # Reads .properties files, supports -D overrides
    │   │   ├── driver/DriverFactory.java      # Creates Chrome / Firefox / Edge / Safari drivers
    │   │   ├── driver/DriverManager.java      # ThreadLocal<WebDriver> storage
    │   │   ├── logging/LogManager.java        # Log4j2 wrapper — single logging entry point
    │   │   └── reporting/IReporter.java       # Pluggable reporting contract
    │   ├── ui/
    │   │   ├── base/BasePage.java             # Parent of all Page Objects
    │   │   ├── base/BaseTest.java             # Parent of all UI test classes
    │   │   ├── utils/WaitUtils.java           # Explicit waits
    │   │   └── utils/BrowserUtils.java        # JS, alerts, windows, frames, actions, screenshots
    │   ├── api/
    │   │   ├── base/BaseApi.java              # Shared REST Assured specifications
    │   │   └── utils/ApiUtils.java            # Response helpers
    │   └── listeners/TestListener.java        # TestNG listener: logs + failure screenshots
    └── test/resources/
        ├── config/config.properties           # UI settings: browser, base URL, timeouts
        ├── config/api.properties              # API settings: base URL, timeout, environment
        ├── suites/ui-suite.xml                # UI smoke + regression suites
        ├── suites/api-suite.xml               # API smoke + regression suites
        └── log4j2.xml                         # Console + rolling file logging
```

---

## Getting Started

### Prerequisites

- Java 21
- Maven 3.x
- Chrome, Firefox, Edge, or Safari installed

-------

## Design Principles

- **Separation of concerns**: the framework provides the engine; test projects provide pages, services, data, and tests
- **No hardcoded values**: URLs, browsers, and timeouts live in properties files
- **Single entry points**: one class for logging, one for configuration, one for driver access
- **Explicit waits only**: no `Thread.sleep()` and no implicit waits
- **Open for extension**: new browsers, reporters, or API services can be added without changing existing code
  
---
## License

This project is licensed under the [MIT License](LICENSE).
