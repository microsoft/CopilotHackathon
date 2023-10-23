# GitHub Copilot Hackathon using Node.js, .NET and Java 

A demo project for running labs to evaluate Copilot viability

- [Goal](#goal)
- [Pre-requisites](#pre-requisites)
- [Work with GitHub Codespaces](#work-with-github-codespaces)
- [Work locally](#work-locally)
- [Instructions](#instructions)

## Goal

The goal of a GitHub Copilot Hackathon is to learn how to use it, using an exercise that consists of building a web server using Node.js with different functionalities, a .NET Web API, or a Java Rest API (either Spring Boot or Quarkus).

GitHub Copilot is an AI-powered code assistant that helps developers write better code faster. It uses machine learning models trained on billions of lines of code to suggest whole lines or entire functions based on the context of what you’re working on. By using Copilot, you can learn how to write better code and improve your productivity.

Remember:

- As you type, GitHub Copilot will make suggestions, which you can accept by pressing Tab.
- If nothing shows up after Copilot writes some lines, press enter and wait a couple of seconds.
- On Windows, macOS or Linux, press Ctrl + Enter, then click Open GitHub Copilot.

## Pre-requisites

**GitHub Copilot access**

A 60-day trial can be requested here: https://github.com/github-copilot/signup

## Work with GitHub Codespaces

The environment is already configured to work with GitHub Codespaces, you can find the configuration files in the .devcontainer folder.

To start programming, just start a new Codespace, and you are ready to go. You don't need to install anything.

### Work locally

**Visual Studio Code**

https://code.visualstudio.com/

**Install Docker**

https://docs.docker.com/engine/install/

**For Node.js**

- [Install Node and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- Install mocha: 

Run:

``` bash
 npm install --global mocha
 npm install axios
```

**For .NET**

[Install .Net](https://dotnet.microsoft.com/download)

**For Java**

- [Install Java](https://learn.microsoft.com/en-us/java/openjdk/install)
- [Install Maven](https://maven.apache.org/install.html)


## Instructions

- [Node Server](./exercisefiles/node/README.md)
- [.NET Web API](./exercisefiles/dotnet/README.md)
- [Java Spring Boot](./exercisefiles/springboot/README.md)
- [Java Quarkus](./exercisefiles/quarkus/README.md)