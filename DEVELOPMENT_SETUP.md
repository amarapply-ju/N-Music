# N-Music Development Environment Setup

## Environment Overview
This document outlines the development environment setup for the N-Music project (v1.0).

### Project Details
- **Type:** Android Kotlin Application
- **Build System:** Gradle with Kotlin DSL
- **Architecture:** MVVM with Jetpack Compose
- **Minimum SDK:** 26 (Android 8.0)
- **Target SDK:** 37
- **Compile SDK:** 37

### Installed Tools & Versions

#### Java
- **Version:** OpenJDK 17.0.18
- **Location:** `/usr/lib/jvm/java-17-openjdk-amd64`
- **JAVA_HOME:** Should be set to `/usr/lib/jvm/java-17-openjdk-amd64`

#### Gradle
- **Version:** 9.4.1 (managed by Gradle wrapper)
- **Wrapper Location:** `gradle/wrapper/gradle-wrapper.jar`
- **Scripts:** `./gradlew` (Unix/Linux/Mac) and `gradlew.bat` (Windows)

#### Android SDK
- **Installation Directory:** `~/Android` (home/jeopardous4/Android/)
- **Command-line Tools:** Located at `~/Android/cmdline-tools/latest/bin/`

### Key Project Dependencies
- **Kotlin:** 2.3.20
- **AGP (Android Gradle Plugin):** 9.1.1
- **Jetpack Compose:** 1.11.0-rc01
- **Material Design 3:** 1.5.0-alpha17
- **Media3 (ExoPlayer):** 1.10.0
- **Room Database:** 2.8.4
- **Hilt (Dependency Injection):** 2.59.2
- **Ktor Client:** 3.4.2
- **Coil (Image Loading):** 3.4.0

### Environment Variables (Required)

Add these to your shell configuration (`~/.bashrc`, `~/.zshrc`, or `~/.config/fish/config.fish`):

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export ANDROID_HOME=$HOME/Android
export PATH=$JAVA_HOME/bin:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools:$PATH
```

### Project Configuration Files

#### local.properties
Located at: `/home/jeopardous4/Downloads/ArchiveTune-1.0/local.properties`

```properties
sdk.dir=/home/jeopardous4/Android
ndk.dir=/home/jeopardous4/Android/ndk
cmake.dir=/home/jeopardous4/Android/cmake
```

#### API Keys (Optional but needed for some features)
Add to `local.properties` or set as environment variables:
- `LASTFM_API_KEY` - For Last.fm integration
- `LASTFM_SECRET` - Last.fm API secret
- `TOGETHER_BEARER_TOKEN` - Together API token
- `CANVAS_BEARER_TOKEN` - Canvas API token

### Build Commands

#### Run with full environment variables:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export ANDROID_HOME=$HOME/Android
cd /home/jeopardous4/Downloads/ArchiveTune-1.0

# Check Gradle version
./gradlew --version

# Download all dependencies
./gradlew dependencies --refresh-dependencies

# Build the project
./gradlew build

# Run tests
./gradlew test

# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing configuration)
./gradlew assembleRelease
```

#### Or use full path to Java:
```bash
export ANDROID_HOME=$HOME/Android
cd /home/jeopardous4/Downloads/ArchiveTune-1.0
/usr/lib/jvm/java-17-openjdk-amd64/bin/java -jar gradle/wrapper/gradle-wrapper.jar build
```

### Project Structure

```
ArchiveTune-1.0/
├── app/                      # Main Android application module
│   ├── build.gradle.kts     # App-level Gradle configuration
│   ├── src/main/kotlin/      # Kotlin source code (MVVM + Compose)
│   ├── res/                  # Android resources (layouts, strings, etc.)
│   └── schemas/              # Database schemas
│
├── betterlyrics/            # Better Lyrics feature module
├── canvas/                  # Canvas rendering module
├── innertube/               # YouTube InnerTube API client
├── kizzy/                   # Kizzy rich presence module
├── kugou/                   # Kugou lyrics module
├── lastfm/                  # Last.fm integration
├── lrclib/                  # LRClib lyrics module
├── shazamkit/               # Shazam integration
├── simpmusic/               # Simple music utilities
│
├── build.gradle.kts         # Root-level Gradle configuration
├── settings.gradle.kts      # Gradle settings
├── gradle/libs.versions.toml # Centralized dependency versions
├── gradle.properties        # Gradle configuration properties
│
└── fastlane/                # Fastlane configuration for CI/CD
```

### Next Steps

1. **Install Android SDK Components:**
   ```bash
   export ANDROID_HOME=$HOME/Android
   yes | ~/Android/cmdline-tools/latest/bin/sdkmanager --licenses
   ~/Android/cmdline-tools/latest/bin/sdkmanager "platforms;android-36" "build-tools;36.0.0"
   ```

2. **Open in Android Studio (Recommended):**
   - Download and install Android Studio
   - Import this project
   - Android Studio will automatically configure the Android SDK

3. **Run a Build:**
   ```bash
   cd /home/jeopardous4/Downloads/ArchiveTune-1.0
   export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
   export ANDROID_HOME=$HOME/Android
   ./gradlew build
   ```

### Troubleshooting

#### Issue: "ANDROID_HOME is not set"
**Solution:** Set environment variable:
```bash
export ANDROID_HOME=$HOME/Android
```

#### Issue: "JAVA_HOME is not set"
**Solution:** Set environment variable:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

#### Issue: "Failed to find package"
**Solution:** Android API 37 might not exist yet. Try API 36 or 35:
```bash
~/Android/cmdline-tools/latest/bin/sdkmanager "platforms;android-36" "build-tools;36.0.0"
```

#### Issue: Gradle wrapper not executable
**Solution:** Make it executable:
```bash
chmod +x /home/jeopardous4/Downloads/ArchiveTune-1.0/gradlew
```

### Resources
- [Official Website](https://archivetune.koiiverse.cloud)
- [GitHub Repository](https://github.com/amarapply-ju/N-Music)
- [Instagram](https://instagram.com/ali.mohsen.maruf)
- [Android Gradle Plugin Documentation](https://developer.android.com/build)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
