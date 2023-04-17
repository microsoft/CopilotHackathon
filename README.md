# Activate GitHub Copilot using Nodejs and .NET Core

Demo project for running labs to evaluate Copilot viability

- [Goal](#goal)
- [Pre-requisites](#pre-requisites)
- [Work with Github Codespaces](#work-with-github-codespaces)
- [Work locally](#work-locally)
- [Instructions](#instructions)

## Goal

The goal of a GitHub Copilot Activate is to learn how to use it, using an exercise that consist of building a web server using Nodejs with different functionalities and a .NET Web API.

 Copilot is an AI-powered code assistant that helps developers write better code faster. It uses machine learning models trained on billions of lines of code to suggest whole lines or entire functions based on the context of what you’re working on. By using Copilot, you can learn how to write better code and improve your productivity.

Remember:

- As you type GitHub Copilot will make suggestions, you can accept them by pressing Tab.
- If nothing shows up after Copilot write some lines , press enter and wait a couple of seconds.
- On Windows or Linux, press Ctrl + Enter, then click Open GitHub Copilot.


## Pre-requisites

**CoPilot access**

A 60 day trial can be requested here: https://github.com/github-copilot/signup

## Work with Github Codespaces

Environment is already configured to work with Github Codespaces, you can find the configuration files in the .devcontainer folder.

To start programming just start a new codespace and you are ready to go, don't need to install anything.

### Work locally

**VisualStudio Code**

https://code.visualstudio.com/

**Install Node and npm**
 https://docs.npmjs.com/downloading-and-installing-node-js-and-npm


**Install mocha**

run:

``` bash
 npm install --global mocha
 npm install axios
```

**Install Docker**

https://docs.docker.com/engine/install/

**Install .NET Core**

https://dotnet.microsoft.com/download


## Instructions

- [Node Server](./exercisefiles/node/README.md)
- [.NET Web API](./exercisefiles/dotnet/README.md)