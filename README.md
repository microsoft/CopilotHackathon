# GitHub Copilot Hackathon

Demo project for running labs to evaluate Copilot viability

- [Goal](#goal)
- [Pre-requisites](#pre-requisites)
- [Work with Github Codespaces](#work-with-github-codespaces)
- [Work locally](#work-locally)
- [Instructions](#instructions)

## Goal

The goal of a GitHub Copilot Hackathon is to learn how to use it, using a set of [exercises (labs)](#labs-instructions) that consist of building a web server using Nodejs with different functionalities, a .NET Web API or a Java Rest API (either Spring Boot or Quarkus).

We have also set two exercises for data engineers and data scientists based on python.

For those who are already familiar with GitHub Copilot, we have also defined a series of [challenges](#challenges-instructions) to test your skills. In this case, you will find an introduction and short guidance to help you solve the challenge, but you will have to do most of the work on your own (with the help of Copilot, of course).

GitHub Copilot is an AI-powered code assistant that helps developers write better code faster. It uses machine learning models trained on billions of lines of code to suggest whole lines or entire functions based on the context of what you’re working on. By using Copilot, you can learn how to write better code and improve your productivity.

Remember:

- As you type GitHub Copilot will make suggestions, you can accept them by pressing Tab.
- If nothing shows up after Copilot write some lines, press enter and wait a couple of seconds.
- Press **Ctrl + Enter**, to see more suggestions.
- Use Copilot chat to support your learning and development.
- Press **Ctrl + i** to start Copilot chat inline within your code. 

## Pre-requisites

**GitHub Copilot access**

If you still do not have an active Copilot license, a 30 day trial can be requested here: https://github.com/github-copilot/signup.

### Work with GitHub Codespaces

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

**For Python**
- [Install Python](https://www.python.org/downloads/)

## Labs instructions

- [Node Server](./exercisefiles/node/README.md)
- [.NET Web API](./exercisefiles/dotnet/README.md)
- [Java Spring Boot](./exercisefiles/springboot/README.md)
- [Java Quarkus](./exercisefiles/quarkus/README.md)
- [Python Data Engineer](./exercisefiles/dataengineer/README.md)
- [Python Data Scientist](./exercisefiles/datascientist/README.md)

## Challenges instructions

- [Develop a shop cart](./challenges/eshop/eshop.md) 
- [Develop a memory game](./challenges/memorygame/memorygame.md)
- [Develop a chat based on websockets](./challenges/chatwebsockets/chatwebsockets.md)
- [Behavior Driven Development (BDD) challenge](./challenges/bdd/README.md)
- [Analysis cryptocurrency market](./challenges/cryptoanalisis/crypto.md)