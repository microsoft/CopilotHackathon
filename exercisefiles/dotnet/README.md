# Goal

The goal is to create a Minimal WebAPI using .NET 7.0 and a corresponding Docker image with the help of GitHub Copilot.
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

``` bash
dotnet run --project .\MinimalAPI\MinimalAPI.csproj
```

### Exercise 1

- Inside `MinimalAPI\Program.cs` add a new Hello World endpoint at `/` that returns a `Hello World!` string.
- Run `dotnet test`
- If test pass you should see something like this:

``` bash
Microsoft (R) Test Execution Command Line Tool Version 17.6.0 (x64)
Copyright (c) Microsoft Corporation.  All rights reserved.

Starting test execution, please wait...
A total of 1 test files matched the specified pattern.

Passed!  - Failed:     0, Passed:     1, Skipped:     0, Total:     1, Duration: < 1 ms - MinimalAPI.Tests.dll
```

### Exercise 2

- Inside `MinimalAPI\Program.cs` add the following endpoints:

- **/DaysBetweenDates**: 

* calculate days between two dates
* receive by query string two parameters `date1` and `date2`, and calculate the days between those two dates.

- **/validatephonenumber**: 

* receive by querystring a parameter called phoneNumber
* validate phoneNumber with Spanish format, for example `+34666777888`
* if phoneNumber is valid return true

- **/validatespanishdni**:

* receive by querystring a parameter called dni
* calculate DNI letter
* if DNI is valid return "valid"
* if DNI is not valid return "invalid"

We will create automated tests to check that the functionality is correctly implemented.  
When the development is completed, we will build a container using Docker

- **/returncolorcode**:

* receive by querystring a parameter called color
* read colors.json file and return the rgba field
* get color var from querystring
* iterate for each color in colors.json to find the color
* return the code.hex field

- **/tellmeajoke**:

Make a call to the joke api and return a random joke using axios
        
- **/moviesbydirector**:

(this will require to browse to https://www.omdbapi.com/apikey.aspx and request a FREE API Key)

Receive by querystring a parameter called director

Make a call to the movie api  and return a list of movies of that director using axios

Return the full list of movies

- **/parseurl**:

Retrieves a parameter from querystring called someurl

Parse the url and return the protocol, host, port, path, querystring and hash

Return the parsed host

- **/listfiles**:

Get the current directory

Get the list of files in the current directory

Return the list of files

- **/calculatememoryconsumption**:

Return the memory consumption of the process in GB, rounded to 2 decimals

- **/randomeuropeancountry**:

Make an array of european countries and its iso codes

Return a random country from the array

Return the country and its iso code

### Exercise 3

- Create a Dockerfile for the Minimal API project.

- Build the image and run the app on port 8080

``` powershell
docker build -t dotnetapp .
docker run -d -p 8080:80 --name dotnetapp dotnetapp
```

# GitHub Copilot Labs exercises

The following tasks can be performed using the Copilot labs add-in, currently PREVIEW functionality, expect some bugs.

Make sure to install the GitHub Copilot labs extension: https://marketplace.visualstudio.com/items?itemName=GitHub.copilot-labs

Open GitHub Copilot extension to see all the available functionality.

- **Explain**

Select the line that has the regex in the validatePhoneNumber method, in EXPLAIN section click "Ask Copilot". You will see an explanation detailing what does each different notations in the regular expression.

- **Language translation**

Select some source code:

``` csharp
var countries = new[] { "Spain", "France", "Germany", "Italy", "Portugal", "Sweden", "Norway", "Denmark", "Finland", "Iceland", "Ireland", "United Kingdom", "Greece", "Austria", "Belgium", "Bulgaria", "Croatia", "Cyprus", "Czech Republic", "Estonia", "Hungary", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Romania", "Slovakia", "Slovenia" };
    return countries[new Random().Next(0, countries.Length)];
```

Then use the "LANGUAGE TRANSLATION" section select python and click "Ask Copilot" button, you should see new code in python.

- **Readable**

Select the content of MakeZipFile

In the BRUSHES section, click in "Readable", see how comments are added and also variables that have short names are renamed to a more understandable name.


-- **Add Types**

TBD

-- **Fix Bug**

In the exercise, there should be no bugs, since most of the code will be done by CoPilot. We can force some errors and then test the debug functionality.

Force some errors like:

``` csharp	
return countries[countries.Length + 1];
```

Then select the "BRUSHES" section press the "Fix Bug" button.

-- **Debug**

Select some lines of text that contains variables, like:

``` chsarp
app.MapGet("/listfiles", () =>
{
    var files = Directory.GetFileSystemEntries(Directory.GetCurrentDirectory());
    return JsonSerializer.Serialize(files);
});
```

Then use the "BRUSHES" section and press the "Debug" button.

-- **Clean**

TBD

-- **List steps**

Select some lines of code that do not have comments and in the "BRUSHES" section press the "List steps" button.


-- **Make robust**

Select some code where you would like to add validation and using the "BRUSHES" section press the "Make robust" button, you will see that additional validation is added.

-- **Chunk**

TBD

-- **Document**

Select some line (e.g. a method or the beginning of the if clause)

    `app.MapGet("/parseurl", (string url) =>`

Then use the "BRUSHES" section and press the "Document" button, you will see that comments explaining what the code does are added before the line.

-- **Test Generation**

TBD
