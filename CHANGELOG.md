# Changelog
All notable changes will be documented here.

## [1.0.0] - 2025-02-07

### Changed
- **Optimized** the logic of **list item composition** in `ListWC.java`.
- **Improved** retry logic for `UiElement.java `**assertions**.
- Updated allurium steps presets
- Made Selenide timeout configurable based on **Allurium** `retryAmount` and `retryIntervalMs` parameters.
    - The configuration timeout is now i**nitially set to `retryAmount * retryIntervalMs`.