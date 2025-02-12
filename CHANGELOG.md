# Changelog
All notable changes will be documented here.

## [1.0.0] - 2025-02-07

### Changed
- **Optimized** the logic of list items initialization in `ListWC.java`.
- **Improved** retry logic for `UiElement.java `**assertions**.
- **Updated** ListWC "should" assertion me[thod. Now it logs all conditions (arguments) into allure step.
- **Updated** allurium steps presets
- **Updated** steps description in `UiSteps.java`
- Made Selenide timeout configurable based on **Allurium** `retryAmount` and `retryIntervalMs` parameters.
    - The configuration tim`]()`eout ]()is now i**nitially set to `retryAmount * retryIntervalMs`.

## [1.0.1] - 2025-02-12

## Changed
- **New** step action "clickAndHold" for UiElement.
- **Updated** initial "retry.amount" set back to the value 10.