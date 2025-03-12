# Changelog
All notable changes will be documented here.

## [1.1.1] - 2025-02-12
### Changed
- **Fixed** "click_and_hold" step template.

## [1.1.0] - 2025-02-12

### Changed
- Global performance optimization.
- **Fixed** "assertCurrentValue" method of select elements.
- **Updated** selenide version to 6.19.1

## [1.0.1] - 2025-02-12

### Added
- **New step action:** `clickAndHold` for `UiElement`.

### Changed
- **Restored** initial `"retry.amount"` value back to `10`.

## [1.0.0] - 2025-02-07

### Changed
- **Optimized** list item initialization logic in `ListWC.java`.
- **Improved** retry logic for `UiElement.java` assertions.
- **Updated** `ListWC` `"should"` assertion method to log all conditions (arguments) into an Allure step.
- **Updated** Allurium steps presets.
- **Updated** step descriptions in `UiSteps.java`.
- **Made** Selenide timeout configurable based on **Allurium** `retryAmount` and `retryIntervalMs` parameters.
  - **Timeout calculation:** `retryAmount * retryIntervalMs`.

