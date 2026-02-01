# Resolver Automation Challenge (Java + Selenium + JUnit)

Automation test case study for the **Automation Test Engineer (Co-op)** application at Resolver.

This repo contains:
- A static web test page (`site/index.html`) and guide (`site/guide.html`)
- Selenium + JUnit tests that automate the required objectives (Tests 1–6)

---

## Tech Stack
- **Java 17**
- **Selenium WebDriver**
- **JUnit 5**
- **Maven**
- **WebDriverManager** (auto-manages browser driver binaries)

---

## Project Structure

```
.
├─ site/
│  ├─ index.html
│  └─ guide.html
└─ src/
   └─ test/
      └─ java/
         └─ com/resolver/challenge/
            ├─ tests/     # JUnit test cases (Tests 1–6)
            ├─ pages/     # Page Object(s) for selectors/actions
            └─ support/   # Driver + wait helpers

```

Design choices:
- **Scoped selectors per test container:** All element lookups start from `#test-<n>-div` via `getTestDiv(n)` to keep selectors stable and avoid accidental matches elsewhere on the page.
- **Explicit waits over sleeps:** Used a small `Waits` helper (visibility/clickable) to reduce flakiness, especially for Test 5 where the button appears after a random delay.
- **Page Object separation:** Encapsulated locators + interactions in `HomePage` so tests focus on assertions/intent (readability + maintainability).
- **Reliable “own text” extraction:** For Test 2, used a JS-based `ownText()` helper to assert list item text without including child badge text.
- **Reusable grid accessor:** Implemented a `gridCellValue(row, col)` method (0-indexed) to retrieve any cell from the table for Test 6. 
- **Test independence**: Each test opens the page fresh in `@BeforeEach` to avoid state bleed across tests. 
- **Driver lifecycle management**: BaseTest owns driver setup/teardown to ensure clean isolation and prevent leaked browser processes.

---

## Prerequisites
- **JDK 17+**
- **Maven 3.9+**
- **Google Chrome** installed
- **Python 3** (only needed to serve the static HTML locally)

Verify:
```bash
java -version
mvn -v
python --version
```

---

## Assumptions

* The static page is served on `http://localhost:8000` using Python’s simple HTTP server.
* Tests are executed against **Chrome**.

---

## How to Run

### 1) Start the local server (serves `site/`)

From the project root:

```bash
cd site
python -m http.server 8000
```

This hosts the page at:

* [http://localhost:8000/index.html](http://localhost:8000/index.html)

Keep this terminal running.

### 2) Run the test suite

Open a new terminal in the project root:

```bash
mvn test
```
