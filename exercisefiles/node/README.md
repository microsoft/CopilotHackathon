# Activate GitHub Copilot using Node.js 

A demo project for running labs to evaluate Copilot viability

## Instructions

- Download to local the exercise file folder
- Open NodeServer.js and begin by writing a Node.js server. Check the first suggestions based on the initial text
- Open test.js file and analyze the current test
- Open a command prompt and run the test (mocha test.js)
- See the result, it should display something like:

``` bash
mocha test.js
server is listening on port 3000

  Node Server
    
    √ should return "key not passed" if key is not passed

  1 passing (34ms)

```

- In the NodeServer.js develop the rest of the methods described in the exercise described in the section below (do not forget to open the color.json file in Visual Studio Code, so Copilot gets all the context to make better recommendations)
- In the Test.js file, add the methods to test the functionality
- Run the tests to verify that everything is working 
- Open the Dockerfile file, and fill it, in order to create a Docker container with a node image that can run the web server
- Create command to run Docker on port 4000
- Test that the application is working on port 4000
- In the **nodeserver.js** file, you can type a new line like //run a curl command to test the server

So we can see how Copilot based on the current file, produces a curl command, to be executed on the command line
- Also you can be more specific, like: //run a curl command to test the daysBetweenDates method

So it generates a test for a specific method 

## Exercise

The exercise consists of building a web server using Node.js that serves requests for various functionality.

The requests that the server must attend to are the following:

- **/Get** : 

Return a hello world message


- **/DaysBetweenDates**: 

Calculate the number of days between two dates specified via query string parameters, 'date1' and 'date2'.

- **/Validatephonenumber**: 

Receive a parameter named "phoneNumber" via the query string. Validate the "phoneNumber" against the Spanish format, which should be in the form of "+34666777888." If the phoneNumber is valid, return "valid"; otherwise, return "invalid.
- **/ValidateSpanishDNI**:

Receive by querystring a parameter called dni
Calculate the DNI letter
if DNI is valid return "valid"
if DNI is not valid return "invalid"

We will create automated tests to check that the functionality is correctly implemented.
When the development is complete, we will build a container using Docker

- **/ReturnColorCode**:

Receive by querystring a parameter called color

read colors.json file and return the rgba field

get color var from querystring

iterate for each color in colors.json to find the color

return the code.hex field

- **/TellMeAJoke**:

Make a call to the joke API and return a random joke using axios
        

- **/MoviesByDirector**:

(To obtain a free API key, you will need to visit https://www.omdbapi.com/apikey.aspx and make a request.)

Receive by querystring a parameter called director

Make a call to the movie api  and return a list of movies of that director using axios

Return the full list of movies

- **/ParseUrl**:

Retrieves a parameter from querystring called someurl

Parse the url and return the protocol, host, port, path, querystring and hash

Return the parsed host

- **/ListFiles**:

Get the current directory

Get the list of files in the current directory

Return the list of files

- **/GetFullTextFile**:

Read sample.txt and return lines that contain the word "Fusce"

(Be careful with this implementation since it normally reads the full content of the file before analyzing it, so memory usage is high and may fail when files are too big)

- **/GetLineByLinefromtTextFile**:

Read sample.txt line by line

Create a promise to read the file line by line, and return a list of lines that contain the word "Fusce"

Return the list of lines

- **/CalculateMemoryConsumption**:

Return the memory consumption of the process in GB, rounded to 2 decimals

- **/MakeZipFile**:

Using zlib create a zip file called sample.gz that contains sample.txt

- **/RandomEuropeanCountry**:

Make an array of European countries and their ISO codes

Return a random country from the array

Return the country and its iso code

## GitHub Copilot Labs exercises

The following tasks can be performed using the Copilot Labs add-in, currently with preview functionality: Expect some bugs.

Make sure to install the GitHub Copilot Labs extension: https://marketplace.visualstudio.com/items?itemName=GitHub.copilot-labs

Open GitHub Copilot extension to see all the available functionality.

- **Explain**

Select the line that has the regex in the validatePhoneNumber method, and in the EXPLAIN section, click "Ask Copilot". You will see an explanation detailing what each different notation does in the regular expression.

- **Language translation**

Select some source code, like this line:

    var randomCountry = countries[Math.floor(Math.random() * countries.length)];

In "LANGUAGE TRANSLATION" section, select Python and click the "Ask Copilot" button. You should see new code in Python.

- **Readable**

Select the content of MakeZipFile

In the BRUSHES section, click on "Readable", see how comments are added and how variables that have short names are renamed to a more understandable name.


-- **Add Types**

TBD

-- **Fix Bug**

In the exercise, there should be no bugs since most of the code will be done by Copilot. We can force some errors and then test the debug functionality.

Force some errors, like:

In a for loop, change the beginning to (change the 0 for a 1):

    for (var i = 1

Select the text, and in the "BRUSHES" section, press the "Fix Bug" button.

-- **Debug**

Select some lines of text that contain variables, like:

    var queryData = url.parse(req.url, true).query;
    var color = queryData.color;
    var colorFound = "not found";

select the text, and in the "BRUSHES" section, press the "Debug" button.


-- **Clean**

TBD


-- **List steps**

Select some lines of code that do not have comments and in the  "BRUSHES" section press the "List steps" button.


-- **Make robust**

Select some text that comes from input, for example variables that come from querystring:

        var queryData = url.parse(req.url, true).query;
        var date1 = queryData.date1;
        var date2 = queryData.date2;

In the  "BRUSHES" section press the "Make robust" button, you will see that additional validation is added.

-- **Chunk**

TBD

-- **Document**

Select some line (e.g. a method or the beggining of the if clause)

    else if (req.url.startsWith('/GetFullTextFile')) 

In the  "BRUSHES" section press the "Document" button, you will see that comments explaining what the code does are added before the line.


-- **Test Generation**

TBD
