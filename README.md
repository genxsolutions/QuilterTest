## Quilter Test – Open Library code challenge

Simple Android application built as part of a senior Android take-home. The app shows the “want-to-read” bookshelf from Open Library (served via [`/people/mekBot/books/want-to-read.json`](https://openlibrary.org/people/mekBot/books/want-to-read.json)), surfaces loading/error states, and reveals book details in a bottom sheet—all using MVVM, RxJava, Retrofit, Room, Hilt, and Jetpack Compose.

### Architecture at a glance

| Layer | Module | Responsibility |
| --- | --- | --- |
| App shell | `app` | Hosts Compose tree and Hilt entry point |
| Feature UI | `feature:books` | ViewModel, list/bottom-sheet Compose UI, Paparazzi & UI tests |
| Domain | `core:domain` | Repository contract + use cases |
| Data | `core:data` | Retrofit service, repository implementation, DI bindings, schedulers |
| Local cache | `core:database` | Room entities + DAO acting as single source of truth |
| Remote | `core:network` | DTOs + Retrofit interface for Open Library |
| Shared | `core:model`, `core:common`, `core:designsystem`, `core:testing` | Kotlin models, scheduler interface, theming, reusable sample data |

### Running the project

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest                 # unit + screenshot tests
./gradlew :feature:books:connectedAndroidTest  # UI test (needs emulator/device)
```

> **Networking note:** If Gradle/Retrofit errors mention `127.0.0.1:8888`, clear the proxy env vars or point them to a reachable proxy—Open Library calls require internet access.

### Behavior summary

1. App start triggers `BooksRepository.refresh()` which calls the want-to-read endpoint, stores the reading list in Room, and emits data via `BookDao.observeBooks()`.
2. `BooksViewModel` exposes a `StateFlow` with loading/error/empty/success states consumed by the Compose UI.
3. The list shows cover art (Coil), title, author; tapping a row opens a bottom sheet with extended details. Light/dark theming uses `QuilterTheme`.

### Future enhancements

- Add paging / endless scroll for larger result sets.
- Provide manual search or filters (e.g., author, subject).
- Offer offline-first strategy with date-based staleness checks.
- Surface retry CTA for error states and distinguish network vs. parsing failures.
- Add accessibility polish (content descriptions, dynamic font scaling previews).

