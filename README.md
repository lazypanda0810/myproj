# SkillUp - Android Course Learning App

A comprehensive Android application for managing and delivering educational courses, including support for Java and Python programming courses with automated testing and certificate generation.

## Overview

SkillUp is an Android-based learning platform that provides users with access to programming courses, interactive lessons, quizzes, and automatically generated certificates upon course completion. The app features a modern Material Design interface with course tracking and progress monitoring.

## Features

- **📚 Course Management**: Browse and view course materials for Java and Python
- **✅ Interactive Tests**: Automated testing system with multiple-choice questions
- **🎓 Certificate Generation**: Generate and share certificates upon course completion
- **📊 Progress Tracking**: Dashboard to monitor course completion and test scores
- **🎨 Material Design UI**: Clean, intuitive user interface following Material Design guidelines
- **💾 Offline Support**: Access course materials without internet connection

## Tech Stack

- **Language**: Java
- **Build System**: Gradle (Kotlin DSL)
- **Platform**: Android SDK
- **IDE**: Android Studio
- **Min SDK**: API 21 (Android 5.0)
- **Target SDK**: API 34 (Android 14)

## Prerequisites

- **Operating System**: Windows 10/11, macOS, or Linux
- **IDE**: Android Studio Koala Feature Drop | 2024.1.2 or higher
- **JDK**: JDK 11 or higher
- **Android SDK**: API 21-34
- **Gradle**: 8.x (included via wrapper)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/lazypanda0810/myproj.git
cd myproj
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select **File → Open**
3. Navigate to the project directory
4. Click **OK**

### 3. Sync Gradle

Android Studio will automatically trigger a Gradle sync. If not:
- Click **File → Sync Project with Gradle Files**
- Wait for dependencies to download

### 4. Run the App

- Select a device or emulator from the device dropdown
- Click the **Run** button (▶️) or press **Shift+F10**
- The app will build and launch on your selected device

## Build Instructions

### Using Android Studio

- **Build**: **Build → Make Project** (Ctrl+F9 / Cmd+F9)
- **Run**: **Run → Run 'app'** (Shift+F10)
- **Debug**: **Run → Debug 'app'** (Shift+F9)
- **Clean**: **Build → Clean Project**

### Command Line (Windows)

```batch
# Build debug APK
gradlew.bat assembleDebug

# Build release APK (requires signing config)
gradlew.bat assembleRelease

# Install debug APK to connected device
gradlew.bat installDebug

# Clean build
gradlew.bat clean

# Run all checks
gradlew.bat check
```

### Command Line (macOS/Linux)

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install to device
./gradlew installDebug

# Clean build
./gradlew clean
```

## Testing

### Unit Tests

```batch
# Run all unit tests
gradlew.bat test

# Run tests for debug build
gradlew.bat testDebugUnitTest
```

### Instrumented Tests

```batch
# Run instrumented tests (requires connected device/emulator)
gradlew.bat connectedAndroidTest
```

### Test Coverage

The project includes automated tests for:
- Course content delivery and navigation
- Quiz/test functionality and scoring
- Certificate generation logic
- Progress tracking calculations

## Project Structure

```
myproj/
├── app/                          # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/myproj/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── CoursesActivity.java
│   │   │   │   ├── CourseDetailActivity.java
│   │   │   │   ├── LessonDetailActivity.java
│   │   │   │   ├── QuizActivity.java
│   │   │   │   ├── QuizResultsActivity.java
│   │   │   │   ├── DashboardActivity.java
│   │   │   │   └── CertificateActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/          # XML layouts
│   │   │   │   ├── values/          # Strings, colors, themes
│   │   │   │   ├── drawable/        # Images and icons
│   │   │   │   └── mipmap-*/        # App launcher icons
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                     # Unit tests
│   │   └── androidTest/              # Instrumented tests
│   └── build.gradle.kts              # Module-level Gradle config
├── gradle/                           # Gradle wrapper files
├── build.gradle.kts                  # Project-level Gradle config
├── settings.gradle.kts               # Project settings
├── requirements.txt                  # Python dependencies (for tooling)
├── DEPENDENCIES.md                   # Detailed dependency documentation
├── JAVA_COURSE_TEST_QUESTIONS.md    # Java course test questions
├── PYTHON_COURSE_TEST_QUESTIONS.md  # Python course test questions
└── README.md                         # This file
```

## Key Components

### Activities

- **MainActivity**: Home screen with navigation to courses and dashboard
- **CoursesActivity**: Browse available courses (Java, Python)
- **CourseDetailActivity**: View course details and lessons
- **LessonDetailActivity**: Display lesson content and code examples
- **QuizActivity**: Take course quizzes with multiple-choice questions
- **QuizResultsActivity**: View quiz results and scores
- **DashboardActivity**: Track course progress and test scores
- **CertificateActivity**: Generate and share completion certificates

### Layouts

All UI layouts are located in `app/src/main/res/layout/`:
- Material Design components
- Responsive layouts for different screen sizes
- Certificate template with SkillUp branding

### Course Content

Course materials are stored in:
- `JAVA_COURSE_TEST_QUESTIONS.md` - Java programming course
- `PYTHON_COURSE_TEST_QUESTIONS.md` - Python programming course

## Configuration

### Gradle Configuration

Update `app/build.gradle.kts` to modify:
- SDK versions (min, target, compile)
- Dependencies
- Build variants
- Signing configurations

### App Name and Branding

Edit `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">SkillUp</string>
```

## Course Content Management

### Adding New Courses

1. Create a new markdown file (e.g., `KOTLIN_COURSE_TEST_QUESTIONS.md`)
2. Follow the existing format with sections for lessons and questions
3. Update the courses list in the app code
4. Add course icon to `res/drawable/`

### Certificate Customization

Certificates include:
- SkillUp logo
- Student name
- Course name
- Completion date
- Course completion percentage
- Test score

Customize in `activity_certificate.xml` and `CertificateActivity.java`.

## Permissions

The app requires the following permissions:
- **WRITE_EXTERNAL_STORAGE**: Save certificates to device storage

## Known Issues / TODOs

- [ ] Add more programming language courses (Kotlin, JavaScript)
- [ ] Implement user authentication and cloud sync
- [ ] Add video lesson support
- [ ] Implement social sharing features
- [ ] Add dark mode support throughout the app
- [ ] Optimize certificate generation performance
- [ ] Add CI/CD pipeline (GitHub Actions)
- [ ] Implement unit tests for all activities

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a Pull Request

### Code Style

- Follow Java coding conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Maintain consistent formatting

## Versioning

This project uses semantic versioning (SemVer):
- **Major**: Breaking changes
- **Minor**: New features (backwards compatible)
- **Patch**: Bug fixes

Current version: **1.0.0**

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For issues, questions, or feature requests:
- **GitHub Issues**: [Create an issue](https://github.com/lazypanda0810/myproj/issues)
- **Email**: support@skillup.com (update with actual contact)

## Acknowledgments

- Built with Android Studio
- Powered by Gradle build system
- Material Design components by Google
- Icons and graphics by SkillUp design team

## Changelog

### Version 1.0.0 (November 2025)
- Initial release
- Java and Python course support
- Certificate generation
- Progress tracking dashboard
- Quiz system implementation

---

**Made with ❤️ by the SkillUp Team**

*This is an educational project. Ensure all course content complies with relevant copyright and licensing requirements.*

