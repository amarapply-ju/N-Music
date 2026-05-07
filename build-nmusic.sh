#!/bin/bash
# N-music APK Builder
# Quick build script for N-music (rebranded ArchiveTune)

set -e

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export ANDROID_HOME=$HOME/Android
export PATH=$JAVA_HOME/bin:$ANDROID_HOME/cmdline-tools/latest/bin:$PATH

PROJECT_ROOT="/home/jeopardous4/Downloads/ArchiveTune-13.2.0"

echo "╔════════════════════════════════════════════════════╗"
echo "║           N-music APK Builder                      ║"
echo "╚════════════════════════════════════════════════════╝"
echo ""

cd "$PROJECT_ROOT"

echo "📦 Building N-music Debug APK (mobile-universal)..."
echo ""

# Clean previous builds (skip with --no-clean)
if [ "$1" != "--no-clean" ]; then
    echo "Cleaning build artifacts..."
    $JAVA_HOME/bin/java -jar gradle/wrapper/gradle-wrapper.jar clean > /dev/null 2>&1
else
    echo "Skipping clean step (--no-clean)."
fi

# Build only the intended variant to avoid compiling all ABIs/flavors
echo "Building debug APK... (mobileUniversalDebug, this may take several minutes)"
$JAVA_HOME/bin/java -jar gradle/wrapper/gradle-wrapper.jar :app:assembleMobileUniversalDebug

DEBUG_APK="app/build/outputs/apk/mobileUniversal/debug/app-mobile-universal-debug.apk"

if [ -f "$DEBUG_APK" ]; then
    echo ""
    echo "╔════════════════════════════════════════════════════╗"
    echo "║              ✅ BUILD SUCCESSFUL                  ║"
    echo "╚════════════════════════════════════════════════════╝"
    echo ""
    echo "📱 APK Location:"
    echo "   $DEBUG_APK"
    echo ""
    APK_SIZE=$(du -h "$DEBUG_APK" | cut -f1)
    echo "📊 APK Size: $APK_SIZE"
    echo ""
    echo "🚀 Next Steps:"
    echo "   1. Install to device:"
    echo "      adb install -r \"$DEBUG_APK\""
    echo ""
    echo "   2. Or copy the APK and install manually:"
    echo "      cp \"$DEBUG_APK\" ~/Desktop/"
    echo ""
    echo "   3. Build release APK (when ready):"
    echo "      ./gradlew assembleRelease"
    echo ""
    echo "✨ Enjoy N-music!"
    echo ""
else
    echo "❌ Build failed. APK not found."
    exit 1
fi
