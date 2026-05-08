# GitHub Pages Setup Guide

## What’s included

### 1. Landing Page
A clean landing page lives in `docs/index.html`.
- It introduces the project.
- It sends users to GitHub Releases for downloads.
- It keeps the repo UI lightweight and static.

### 2. Pages Configuration
The site is ready to be served from the `docs/` folder using GitHub Pages.
- **Config**: `docs/_config.yml`
- **Source**: `main` branch, `/docs` folder

## To enable GitHub Pages

1. Open the repository on GitHub.
2. Go to **Settings → Pages**.
3. Set **Source** to `Deploy from a branch`.
4. Choose **Branch** = `main` and **Folder** = `/docs`.
5. Save the setting and wait for GitHub to finish the build.

## Repository description

Suggested description:
- **Nothing OS inspired music player for Android**

## Download source

There is only one supported download source right now:
- **GitHub Releases**: `https://github.com/alimohsenmaruf/N-Music/releases/tag/Latest`

## Notes

- No extra Pages deployment workflow is needed if you are using GitHub Pages from the repository settings.
- The release workflow already creates GitHub Releases on version bumps.
- The app itself uses external APIs and services, but there is no separate repo-hosted backend module.
