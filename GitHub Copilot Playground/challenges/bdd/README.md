# Behavior Driven Development (BDD) challenge

## Introduction

Behavior Driven Development (BDD) is a software development process that emerged from Test Driven Development (TDD). BDD is a refinement of TDD and is intended to make TDD and Agile development more accessible and intuitive to newcomers and experts alike.

This challenge is intended to utilize Github Copilot to help you write BDD tests for the following user story:

> As a user, I want to be able to interact with the Author REST API endpoints so that I can manage authors in the system. This includes being able to create a new author, update an existing author's details, retrieve details about a specific author or all authors, and delete an author from the system. An Author has an id and a name.

This user story has already a working  implementation in the [AuthorController.java](src/main/java/com/microsoft/hackathon/demo/controller/AuthorController.java) file.

## Challenge

Your challenge is to write BDD tests for the user story above and all the test must pass based on the implementation in the [AuthorController.java](src/main/java/com/microsoft/hackathon/demo/controller/AuthorController.java) file.

**Hints**

Use Copilot chat to help you define the features and scenarios for the user story. Consider using the [Gherkin](https://cucumber.io/docs/gherkin/) syntax to define the feature and scenarios and use the [Cucumber](https://cucumber.io/docs/cucumber/) framework to run the tests.
