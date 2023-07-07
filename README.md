# GitHub Copilot Hackaton using Nodejs, .NET and Java 

Demo project for running labs to evaluate Copilot viability

- [Goal](#goal)
- [Pre-requisites](#pre-requisites)
- [Work with Github Codespaces](#work-with-github-codespaces)
- [Work locally](#work-locally)
- [Instructions](#instructions)

## Goal

The goal of a GitHub Copilot Hackaton is to learn how to use it, using an exercise that consist of building a web server using Nodejs with different functionalities, a .NET Web API or a Java Spring Boot API.

GitHub Copilot is an AI-powered code assistant that helps developers write better code faster. It uses machine learning models trained on billions of lines of code to suggest whole lines or entire functions based on the context of what you’re working on. By using Copilot, you can learn how to write better code and improve your productivity.

Remember:

- As you type GitHub Copilot will make suggestions, you can accept them by pressing Tab.
- If nothing shows up after Copilot write some lines , press enter and wait a couple of seconds.
- On Windows, MacOS or Linux, press Ctrl + Enter, then click Open GitHub Copilot.

## Pre-requisites

**GitHub Copilot access**

A 60 day trial can be requested here: https://github.com/github-copilot/signup

## Work with GitHub Codespaces

Environment is already configured to work with Github Codespaces, you can find the configuration files in the .devcontainer folder.

To start programming just start a new codespace and you are ready to go, don't need to install anything.

### Work locally

**VisualStudio Code**

https://code.visualstudio.com/

**Install Docker**

https://docs.docker.com/engine/install/

**For Nodejs**

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
