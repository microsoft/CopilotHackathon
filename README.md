# Proof of Concept for Copilot using Nodejs 

Demo project for running a Proof of Concept for Copilot viability

## Pre-requisites

**CoPilot access**

A 60 day trial can be requested here: https://github.com/github-copilot/signup

**VisualStudio Code**

https://code.visualstudio.com/

**Install Node and npm**
 https://docs.npmjs.com/downloading-and-installing-node-js-and-npm


**Install mocha**

run npm install --global mocha

**Install Docker**

https://docs.docker.com/engine/install/

## Goal

The goal of a GitHub Copilot proof of concept is to learn how to use it.

 Copilot is an AI-powered code assistant that helps developers write better code faster. It uses machine learning models trained on billions of lines of code to suggest whole lines or entire functions based on the context of what you’re working on. By using Copilot, you can learn how to write better code and improve your productivity.

Remember:

- As you type GitHub Copilot will make suggestions, you can accept them by pressing Tab.
- If nothing shows up after Copilot write some lines , press enter and wait a couple of seconds.
- On Windows or Linux, press Ctrl + Enter, then click Open GitHub Copilot.

## Exercise

The exercise consist of building a web server using Nodejs that serves the request of various functionality.

The requests that the server must attend are the following:

**/get** : Return a hello world message


**/daysBetweenDates**: Calculate days between two dates

receive by query string 2 parameters date1 and date 2 , and calcualte the days that are between those two dates.

**/validatephonenumber**: 
Receive by querystring a parameter called phoneNumber 
validate phoneNumber with Spanish format, for example +34666777888
if phoneNumber is valid return "valid"
if phoneNumber is not valid return "invalid"

**validateSpanishDNI** 
Receive by querystring a parameter called dni
calculate DNI letter
if DNI is valid return "valid"
if DNI is not valid return "invalid"

We will create automated tests to check that the functionality is correctly implemented.
When the development is completed, we will build a container using Docker

**returnColorCode**
Receive by querystring a parameter called color

read colors.json file and return the rgba field

get color var from querystring

iterate for each color in colors.json to find the color

return the code.hex field


## Instructions

- Download to local the exercicefile folder
- Open NodeServer.js and begin by writing a Nodejs server, check the first suggestions based on the initial text
- Open test.js file and analyze the current test
- Open a command prompt and run the test (mocha test.js)
- See the result, it should display something like:

mocha test.js
server is listening on port 3000

  Node Server
    
    √ should return "key not passed" if key is not passed

  1 passing (34ms)

- In the NodeServer.js develop the rest of the methods described in the Exercise (do not forget to open color.json file in Visual Studio Code, so CoPilot get all the context to make better recommendations)
- In the Test.js file add the methods to test the funcionality
- Run the tests to verify that all is working 
- Open the dockerfile file, and fill it, in order to create a docker container with a node image that can run the web server
- Create command to run docker in port 4000
- Test that the application is working in port 4000
- In the nodeserver.js file, you can type a new line like //run a curl command to test the server

So we can see how CoPilot based on the current file produces a curl command, to be executed in command line
- Also you can be more specific like: //run a curl command to test the daysBetweenDates method

So it generates a test for a specific method 
