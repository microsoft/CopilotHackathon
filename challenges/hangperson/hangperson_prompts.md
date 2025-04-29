# Prompt Start

Here are some prompts to guide you through setting up the initial Hangperson project structure using Maven and adding basic application and test code.

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

2.  **Create/Modify Main Application Class:**
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

3.  **Create/Modify Test Class:**
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