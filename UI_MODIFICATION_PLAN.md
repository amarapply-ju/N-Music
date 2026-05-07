# UI Modification Plan — "N-music" (white-label)

## Purpose
Make UI changes so the app looks and feels like a Nothing.tech product while keeping all existing features intact. This is strictly a visual/branding change: no feature or behavior changes.

## Goals
- Apply a dot-matrix font to all title texts (primary headings, screen titles).
- Implement three themes: Light, Dark, and Extra Dark (no other customization options).
- White-label the app as **N-music** while keeping a credit note that this is built on top of the project.
- Remove all "About" pages and related branding; replace developer credits with user-provided handle.
- Keep changes minimal, reversible, and well-documented.

## Non-goals
- No feature changes or API changes.
- No experimental UI animations or brand-new screens.
- No per-user customization beyond the three themes.

## High-level Steps (mapped to tracked todos)
1. Add dot-matrix font resource to the `app` module.
2. Apply the dot-matrix font to all title typography styles and navigation/app bar titles.
3. Implement Light, Dark, and Extra Dark themes, wire them into the app theme switcher (or default settings), and ensure proper contrast/legibility.
4. White-label: update `app_name`, launcher icons (if white-label assets provided), and visible strings to `N-music` while adding a small credit line where appropriate.
5. Replace all "About" pages; remove references in navigation and settings screens.
6. In the Developers / Credits section, add the Instagram handler: `@ali.mohsen.maruf` and remove other About credits as requested.
7. QA: compile, smoke-test key flows (home, playback, settings, login), check theme switching, verify no regressions.

## Detailed Implementation Notes

### 1) Add dot-matrix font
- File(s) to add:
  - `app/src/main/res/font/dot_matrix.ttf` (binary font file)
- Tasks:
  - Obtain a suitable dot-matrix font (ensure license allows bundling). If you provide a font file, place it at the above path; otherwise I can show recommended fonts and a safe default.
  - Update Compose `FontFamily` or XML `font` resources to include the new font.

### 2) Apply font to titles
- Targets:
  - All app bar / screen titles
  - Major headings in Compose `Text` components that use `headlineSmall`, `headlineMedium`, `h6`, or custom `title` styles
  - Dialog titles and other top-level headings
- Implementation:
  - Update the app's theme typography (likely in `app/src/main/kotlin/.../ui/theme/Theme.kt` or `ui/theme/Type.kt`) to set the dot-matrix `FontFamily` for headline/title styles only; keep body fonts unchanged for readability.
  - Use specific style tokens: `headlineLarge`, `headlineMedium`, `headlineSmall`, `titleLarge` as appropriate.
  - Add automated lint note: avoid applying decorative font to long paragraphs.

### 3) Implement Light, Dark, Extra Dark themes
- Design:
  - Light: primary light surfaces, accent color palette aligned with Nothing aesthetic (monochrome + bright accent). Keep existing colors mostly intact unless you want a full palette change.
  - Dark: standard dark Material theme.
  - Extra Dark: 100% black surfaces (for OLED), stronger contrast for UI elements.
- Implementation:
  - Add three theme configurations (Compose `ColorPalette` or XML values): `Theme.Light`, `Theme.Dark`, `Theme.ExtraDark`.
  - Expose a simple switch in Settings or honor system theme + an override in the app settings (we can add a single select preference with three options).
  - Ensure navigation bars, status bars, scrim colors are set correctly per theme.

### 4) White-label as `N-music`
- Strings to update:
  - `app/src/main/res/values/strings.xml` → change `app_name` to `N-music`
  - Any visible occurrences of the old project name in UI copy → replace with `N-music` or neutral copy.
- Assets:
  - Launcher icons and splash — if you provide new `N-music` assets, replace `mipmap-*` images; otherwise keep current icons but rename strings.
- Credits/Attribution:
  - Add a small credit string in Settings, keep it subtle.

### 5) Remove About pages
- Locate About screens (likely `AboutActivity`, `AboutFragment`, or Settings preference screens with `about_*` strings).
- Remove navigation entries linking to About, and delete the UI files (or hide them) so no About content is visible.
- Keep license files in repo (LICENSE, PRIVACY.md) but do not surface them in app UI unless explicitly requested.

### 6) Update Developer Credits
- Add the requested Instagram handle in the developer/credits section:
  - Replace or add entry: `@ali.mohsen.maruf`
- Ensure the developer section is reachable from Settings and that the contact/credits now reflect the requested handle.

### 7) QA & Verification
- Build debug and release APKs.
- Verify typography is applied only to titles and headings.
- Verify theme switching for Light/Dark/Extra Dark.
- Verify About pages removed and `N-music` visible across app.
- Smoke test playback, login, playlists, and settings.

## Deliverables (per step)
- `app/src/main/res/font/dot_matrix.ttf` (or instructions to provide)
- Theme and typography code changes (Kotlin files in `ui.theme` package)
- `strings.xml` updates for `app_name` and credits
- Small settings change: theme selector (3 options)
- Removal/hiding of About pages and navigation entries
- `UI_MODIFICATION_PLAN.md` (this file)

## Rollback plan
- Keep all modifications in a dedicated feature branch.
- Apply changes in small commits per task so we can revert specific commits if needed.

## Licensing & assets
- Provide or confirm the font license before bundling.
- Provide `N-music` logos if you want a full white-label icon replacement.

## Timeline & priorities
- Priority 1: Add font and apply to titles (low-risk, immediate visual impact).
- Priority 2: Implement themes (important for UX).
- Priority 3: White-label strings and developer credit.
- Priority 4: replace About pages and final QA.

---

## Next action
Confirm if you want me to (A) pick a permissively licensed dot-matrix font and add it, or (B) you will provide the font and any `N-music` assets (logo, icon). After confirmation I'll implement Step 1.
