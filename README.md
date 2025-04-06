# Jabberpoint

> Made by [Lucas Lubbers](https://github.com/LucasLubbers) and [Daan Rijfers](https://github.com/DaanRijfers)

JabberPoint is a presentation application built with Java 21 and Maven.

## Getting Started

### Prerequisites
- Java 21 JDK
- Maven 3.6.0 or higher

### Running the Application
#### Option 1: Download Pre-built JAR
You can download the latest `.jar` file from the [Releases](https://github.com/LucasLubbers/JabberPoint/releases) page and run it with:
```bash
java -jar your-project-1.0-SNAPSHOT.jar
```

#### Option 2: Build from source
```
git clone git@github.com:LucasLubbers/JabberPoint.git
cd JabberPoint
```

##### Clean and build the project:
```bash
mvn clean install -DskipTests
```

#### Run project through jar file:
```bash
java -jar target/your-project-1.0-SNAPSHOT.jar
```

##### Run the tests:
```bash
mvn clean test
```

### ⚠️ Note on Tests
Some tests may trigger pop-up windows. Close them manually to allow the tests to complete.
