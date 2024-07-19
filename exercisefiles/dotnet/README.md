# Goal

The goal is to create a Minimal WebAPI using .NET 8.0 and a corresponding Docker image with the help of GitHub Copilot.
Follow the instructions below and try to use GitHub Copilot as much as possible.
Try different things and see what GitHub Copilot can do for you, like generating a Dockerfile or a class, add comments, etc.

> Make sure GitHub Copilot is configure and enabled for the current laguage, just check the status bar on the bottom right corner of VS Code.

## Instructions

The `dotnet` folder contains the `MinimalAPI.sln` solution, with 2 projects:

- `MinimalAPI` is a minimal WebAPI project created using `dotnet new webapi -minimal`
- `MinimalAPI.Tests` is a minimal xUnit project created using `dotnet new xunit`

To run the tests, open a terminal in the `dotnet` folder and run:

``` bash
dotnet test
```

To run the app, open a terminal in the `dotnet` folder and run:

**Windows environments**
``` bash
dotnet run --project .\MinimalAPI\MinimalAPI.csproj
```

**Codespaces, Linux & Unix environments**
``` bash
dotnet run --project ./MinimalAPI/MinimalAPI.csproj
```

### Exercise 1: Introduction

- Inside `MinimalAPI\Program.cs` add a new Hello World endpoint at `/` that returns a `Hello World!` string. You can test the Copilot inline feature by pressing `ctrl + i`. Then write in the text box the desired behaviour. 
- Run `dotnet test`
- If test pass you should see something like this:

``` bash
Microsoft (R) Test Execution Command Line Tool Version 17.6.0 (x64)
Copyright (c) Microsoft Corporation.  All rights reserved.

Starting test execution, please wait...
A total of 1 test files matched the specified pattern.

Passed!  - Failed:     0, Passed:     1, Skipped:     0, Total:     1, Duration: < 1 ms - MinimalAPI.Tests.dll
```

### Exercise 2: Building new functionalities


Inside `MinimalAPI\Program.cs` add the following endpoints using the help of Copilot:

- **/DaysBetweenDates**: 

    * calculate days between two dates
    * receive by query string two parameters `date1` and `date2`, and calculate the days between those two dates.

> **_NOTE:_** Use above information inside the Copilot inline feature in the `Program.cs` file. Press enter and wait for Copilot to suggest you the code.

- **/validatephonenumber**: 

    * receive by querystring a parameter called phoneNumber
    * validate phoneNumber with Spanish format, for example `+34666777888`
    * if phoneNumber is valid return true

> **_NOTE:_** Use above information inside a comment in the `Program.cs` file. Press enter and wait for Copilot to suggest you the code.

- **/validatespanishdni**:

    * receive by querystring a parameter called dni
    * calculate DNI letter
    * if DNI is valid return "valid"
    * if DNI is not valid return "invalid"

> **_NOTE:_** Use above information inside a comment in the `Program.cs` file. In this case, you may want to see multiple solutions from Copilot to pick the one that best fits the way to calculate the letter. In order to see the firs 10 suggestions from Copilot press `ctrl + enter`. 

- **/returncolorcode**:

    * receive by querystring a parameter called color
    * read colors.json file and return the rgba field
    * get color var from querystring
    * iterate for each color in colors.json to find the color
    * return the code.hex field

> **_NOTE:_** Lets try Copilot chat now. Paste the above information and make it as detailed as possible in the Copilot chat text box. Copilot will use by default the open file as context in order to generate the suggestion.

- **/tellmeajoke**:

    * Make a call to the joke api and return a random joke

> **_NOTE:_** Here's example where you might need to use you own knowledge and judgement
to validate that Copilot follows best practices. Just because Copilot mimic 
what many developers do, doesn't always mean it's the correct way. You might need 
to be extra specific in your prompt to let Copilot know what's best practices. 
_Hint: Pay attention to HttpClient._
        
- **/moviesbydirector**:

    * Receive by querystring a parameter called director
    * Make a call to the movie api and return a list of movies of that director
    * Return the full list of movies

> **_NOTE:_** This will require to browse to https://www.omdbapi.com/apikey.aspx and request a FREE API Key

- **/parseurl**:

    * Retrieves a parameter from querystring called someurl
    * Parse the url and return the protocol, host, port, path, querystring and hash
    * Return the parsed host

> **_NOTE:_** Copilot can help you learn new frameworks.

- **/listfiles**:

    * Get the current directory
    * Get the list of files in the current directory
    * Return the list of files

> **_NOTE:_** Copilot can also help with these kind of commands locally. The feature is called Copilot in the CLI. You can learn more information about this feature [here](https://docs.github.com/en/copilot/github-copilot-in-the-cli/about-github-copilot-in-the-cli).

- **/calculatememoryconsumption**:

    * Return the memory consumption of the process in GB, rounded to 2 decimals

- **/randomeuropeancountry**:

    * Make an array of european countries and its iso codes
    * Return a random country from the array
    * Return the country and its iso code

### Exercise 3: Document the code

Documenting code is always a boring and painful task. However, we can use Copilot to document it for us. In the chat, ask Copilot to document the Program.cs file. 

### Exercise 4: Building tests

We will create automated tests to check that the functionality of the previous endpoints is correctly implemented. The tests should be together in the `MinimalAPI.Tests\IntegrationTests.cs` file.

You can leverage Copilot to run the tests. There is a `/tests` command that you can directly run from Copilot Chat or by selecting the piece of code you want to create tests for and using the Copilot inline feature. 

### Exercise 5: Create a Dockerfile

Now that we have the new functionality added and tests covering it, lets create a Dockerfile for the Minimal API project. 

- Build the image using Copilot and run the app on port 8080

``` powershell
docker build -t dotnetapp .
docker run -d -p 8080:80 --name dotnetapp dotnetapp
```

## Summary

With the previous exercises you have gone through some common activities that developers usually run:
- Create new features in the code
- Work with external APIs
- Create documentation
- Create tests

However, there are many other things that Copilot can helkp you with. Feel free to explore other slash command in the Copilot chat like:
- `/fix`: to fix the problems in your code
- `/explain`: for Copilot to explain you what the code does