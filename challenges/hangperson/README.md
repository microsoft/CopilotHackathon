# Java Console Application Setup (HangpersonApp) with Copilot Assistance

This document describes how to set up a new testable Java console application using Maven, leveraging GitHub Copilot for assistance.

## Prerequisites

*   Java Development Kit (JDK) installed (version 8 or later recommended).
*   Apache Maven installed.
*   GitHub Copilot extension enabled in your IDE.

## Setup Steps

1.  **Create Project Structure:**
    Open your terminal or command prompt, navigate to the directory where you want to create the project (the directory containing this `README.md` file), and run the following Maven command to generate a basic project structure:

    ```bash
    mvn archetype:generate -DgroupId=com.example -DartifactId=hangpersonapp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    ```

    This will create a `hangpersonapp` subdirectory with the standard Maven layout (`src/main/java`, `src/test/java`, `pom.xml`).

1.  **Navigate into the Project:**
    ```bash
    cd hangpersonapp
    ```

1.  **Create a new vscode window to contain only this app**
    ```bash
    code .
    ```
    Start using copilot in that new window (you can open this md file on that window also if needed)

1.  **Update `pom.xml` for Testing and Java Version:**
    Open the `pom.xml` file.
    *   **Prompt Copilot:** "Set the Java compiler source and target versions to 1.8."
    *   **Prompt Copilot:** "Ensure the JUnit 5 Jupiter engine dependency is included for testing."
    *   **Prompt Copilot:** "Add the maven-surefire-plugin configured for JUnit 5."
    *   **Prompt Copilot:** (Optional) "Add the maven-assembly-plugin to create an executable JAR with dependencies, setting the main class to com.example.App."

    *Example `pom.xml` structure after Copilot's changes:*
    ```xml
    <!-- filepath: pom.xml -->
    <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
      <junit.jupiter.version>5.8.2</junit.jupiter.version> <!-- Or latest -->
    </properties>

    <dependencies>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.jupiter.version}</version>
        <scope>test</scope>
      </dependency>
      <!-- Other dependencies -->
    </dependencies>

    <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>3.0.0-M5</version> <!-- Or latest compatible -->
          </plugin>
          <!-- Optional Assembly Plugin -->
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-assembly-plugin</artifactId>
              <version>3.3.0</version> <!-- Or latest -->
              <configuration>
                  <archive>
                      <manifest>
                          <mainClass>com.example.App</mainClass> <!-- Verify this -->
                      </manifest>
                  </archive>
                  <descriptorRefs>
                      <descriptorRef>jar-with-dependencies</descriptorRef>
                  </descriptorRefs>
              </configuration>
              <executions>
                  <execution>
                      <id>make-assembly</id>
                      <phase>package</phase>
                      <goals>
                          <goal>single</goal>
                      </goals>
                  </execution>
              </executions>
          </plugin>
      </plugins>
    </build>
    ```

4.  **Create/Modify Main Application Class:**
    Navigate to `src/main/java/com/example/App.java`.
    *   **Prompt Copilot:** "Create a basic main method that prints 'Hello HangpersonApp!'."
    *   **Prompt Copilot:** "Add a public method `getGreeting` that returns the string 'Hello HangpersonApp!'."

    *Example `App.java` after Copilot's changes:*
    ```java
    // filepath: src/main/java/com/example/App.java
    package com.example;

    public class App {
        public static void main(String[] args) {
            System.out.println("Hello HangpersonApp!");
            // Add application logic here
        }

        public String getGreeting() {
            return "Hello HangpersonApp!";
        }
    }
    ```

1.  **Create/Modify Test Class:**
    Navigate to `src/test/java/com/example/AppTest.java`.
    *   **Prompt Copilot:** "Convert this test class to use JUnit 5."
    *   **Prompt Copilot:** "Write a JUnit 5 test method named `testGetGreeting` that asserts the `getGreeting` method of the `App` class returns 'Hello HangpersonApp!'."

    *Example `AppTest.java` after Copilot's changes:*
    ```java
    // filepath: src/test/java/com/example/AppTest.java
    package com.example;

    import org.junit.jupiter.api.Test;
    import static org.junit.jupiter.api.Assertions.*;

    class AppTest {
        @Test
        void shouldAnswerWithTrue() {
            assertTrue(true); // Keep or remove placeholder
        }

        @Test
        void testGetGreeting() {
            App app = new App();
            assertEquals("Hello HangpersonApp!", app.getGreeting());
        }
    }
    ```

## Building and Running

1.  **Compile:**
    ```bash
    mvn compile
    ```

2.  **Run Tests:**
    ```bash
    mvn test
    ```

3.  **Package (Create JAR):**
    ```bash
    mvn package
    ```
    This will create JAR files in the `target/` directory. If you included the `maven-assembly-plugin`, it will create an executable JAR with dependencies.

4.  **Run the Application (using the executable JAR):**
    ```bash
    java -jar target/hangpersonapp-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
    *(Adjust the JAR filename based on your project's version)*

    Alternatively, run directly using Maven:
    ```bash
    mvn exec:java -Dexec.mainClass="com.example.App"
    ```
    *(Make sure to replace `com.example.App` with your actual main class if different)*

## Actual game implementation

1. Start implementing a console game where you guess a random word.

    Hangperson (traditionally known as Hangman) is a classic word-guessing game. One player thinks of a word or phrase, and the other player tries to figure it out by guessing letters one at a time. For each incorrect guess, a part of a stick figure being hanged is drawn. The goal is to guess the word before the drawing is completed.

    You can use that description for Copilot, but try to ask only for small parts of the implementation at once.

    You can, e.g., first use ask mode to plan what parts you could ask agent mode to implement at once and how to limit agent mode not to try to do the whole game in one go.

    There are nouns and verbs available in this directory for you to use as words or in any way you would like. Please see `wordnet.md` for the license for nouns and verbs.

2. When the game is ready or in good shape, consider adding tests if none exist yet.

    Before adding tests, it would be good to refactor the game to be modular enough. Try to extract the game engine, input parsing, and rendering. If you can come up with better modules with Copilot, try to use those.

## Challenging features

1. Make a game server and a client.

2. Make bots (AI opponents).

3. Implement chat between clients
