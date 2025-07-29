# Phrasal Verbs Hero 📱

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

A modern Android application designed to help users learn and practice phrasal verbs in English through interactive exercises and quizzes.

## 📱 Overview

Phrasal Verbs Hero is a learning application that makes mastering English phrasal verbs engaging and effective. The app features a clean, modern UI built with Jetpack Compose and follows modern Android development best practices.

[Screenshot placeholder]

## 🚀 Features

- Interactive phrasal verbs practice sessions
- Quiz mode for testing knowledge
- Comprehensive definitions screen
- Offline support
- Modern material design interface
- Airplane mode detection
- Multiple build variants (Local, QA, Production)

## 🛠️ Technology Stack

- **Language:** Kotlin
- **Minimum SDK:** [Version inferred from build files]
- **Target SDK:** Android 31+
- **Architecture:** Clean Architecture with MVVM
- **Framework & Libraries:**
  - Jetpack Compose for UI
  - Dagger Hilt for dependency injection
  - Android Navigation Component
  - Coroutines for asynchronous operations
  - AndroidX components

## 📋 Prerequisites

- Android Studio Arctic Fox or newer
- JDK 11 or higher
- Android SDK with API 31+
- Gradle 7.0+

## ⚙️ Installation & Setup

1. Clone the repository:
```bash
git clone https://github.com/[username]/android-phrasal-verbs-hero.git
```

2. Open the project in Android Studio

3. Configure your `local.properties` file with required SDK path

4. Sync project with Gradle files

5. Create a Firebase project and add `google-services.json` to the app directory

## 🏗️ Project Structure

The project follows a modular architecture with these main components:

```
├── app/                  # Main application module
├── core/                 # Core functionality module
├── core_ui/             # Shared UI components module
└── gradle/              # Gradle configuration
```

### Key Components:

- `app` - Main application module containing activities and UI
- `core` - Business logic and data operations
- `core_ui` - Reusable UI components and theme definitions

## 🔧 Configuration

The application supports three build variants:
- `localDebug` - For local development
- `qaDebug` - For testing environment
- `prodDebug` - For production environment

## 📱 Running the Application

1. Select the desired build variant in Android Studio
2. Click "Run" (⌃R on macOS or F5 on Windows/Linux)
3. Select your target device/emulator

## 🧪 Testing

The project includes both unit tests and instrumentation tests:

```bash
# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

---

## 📚 Architecture

The app follows Clean Architecture principles with MVVM pattern:
- Presentation Layer (UI, ViewModels)
- Domain Layer (Use Cases)
- Data Layer (Repositories, Data Sources)

[Architecture diagram placeholder]

---

**Note**: This application is currently in active development. Features and implementation details may change.
