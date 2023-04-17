// write a nodejs server that will expose a method call "get" that will return the value of the key passed in the query string
// example: http://localhost:3000/get?key=hello
// should return the value of foo
// if the key is not passed, return "key not passed"
// if the key is passed, return "hello" key

const http = require('http');
const url = require('url');
const fs = require('fs');

const server = http.createServer((req, res) => {


    
      if (req.url.startsWith('/DaysBetweenDates')) {
        //calculate days between two dates

        //get dates from querystring
        var queryData = url.parse(req.url, true).query;
        var date1 = queryData.date1;
        var date2 = queryData.date2;

        //convert dates to milliseconds
        var date1_ms = Date.parse(date1);
        var date2_ms = Date.parse(date2);

        //calculate difference in milliseconds
        var difference_ms = date2_ms - date1_ms;

        //convert to days and return
        res.end(Math.round(difference_ms / 86400000) + " days");

    }     else if (req.url.startsWith('/Validatephonenumber')) {

        //get phoneNumber var from querystring
        var queryData = url.parse(req.url, true).query;
        var phoneNumber = queryData.phoneNumber;


        //validate phoneNumber with Spanish format
        var regex = /^(\+34|0034|34)?[ -]*(6|7)[ -]*([0-9][ -]*){8}$/;

        //if phoneNumber is valid return "valid"
        if (regex.test(phoneNumber)) {
            res.end("valid");
        }
        //if phoneNumber is not valid return "invalid"
        else {
            res.end("invalid");
        }
    } else if (req.url.startsWith('/ValidateSpanishDNI')) {
        var queryData = url.parse(req.url, true).query;
        var dni = queryData.dni;

        // calculate DNI letter
        var dniLetter = dni.charAt(dni.length - 1);
        var dniNumber = dni.substring(0, dni.length - 1);
        var dniLetterCalc = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(dniNumber % 23);

        //if DNI is valid return "valid"
        if (dniLetter == dniLetterCalc) {
            res.end("valid");
        }
        //if DNI is not valid return "invalid"
        else {
            res.end("invalid");
        }
        
    } else if (req.url.startsWith('/ReturnColorCode')) {

        //read colors.json file and return the rgba field
        var colors = fs.readFileSync('colors.json', 'utf-8');
        var colorsObj = JSON.parse(colors);

        //get color var from querystring
        var queryData = url.parse(req.url, true).query;
        var color = queryData.color;
        var colorFound = "not found";

        //for each color in colors.json
        for (var i = 1; i < colorsObj.length; i++) {
            //if color is found return the color code
            if (colorsObj[i].color == color) {
                colorFound = colorsObj[i].code.hex;
            }
        }

        res.end(colorFound);

    }
    else if (req.url.startsWith('/SendEmail')) {
    }
    else if (req.url.startsWith('/TellMeAJoke')) {

        //make a call to the joke api and return a random joke using axios
        const axios = require('axios');

        axios.get('https://official-joke-api.appspot.com/random_joke')
            .then(function (response) {
                // handle success
                res.end(response.data.setup + " " + response.data.punchline);
            }
            )
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });
    }

    //method that gets the name of a director and retrieves from an api the list of movies of that director
    else if (req.url.startsWith('/MoviesByDirector')) {

        //get a director name from querystring
        var queryData = url.parse(req.url, true).query;
        var director = queryData.director;

         //make a call to the movie api omdbapi.com and return a list of movies of that director using axios
        const axios = require('axios');

        axios.get('http://www.omdbapi.com/?apikey=XXXXXXX&s=' + director)
            .then(function (response) {
                
                //return the full list of movies
                var movies = "";
                for (var i = 0; i < response.data.Search.length; i++) {
                    movies = movies + response.data.Search[i].Title + ", ";
                }

                res.end(movies);
                
            }
            )
            .catch(function (error) {
                // handle error
                console.log(error);
            }
            )
            .then(function () {
                // always executed
            }
            );


  
    }
    //If url equals to ParseUrl
    else if (req.url.startsWith('/ParseUrl')) {

        //retrieves a parameter from querystring called someurl
        var queryData = url.parse(req.url, true).query;
        var someUrl = queryData.someurl;

        //parse the url and return the protocol, host, port, path, querystring and hash
        var urlObj = new URL(someUrl);

        var protocol = urlObj.protocol;
        var host = urlObj.host;
        var port = urlObj.port;
        var path = urlObj.pathname;
        var querystring = urlObj.search;
        var hash = urlObj.hash;

        //return the parsed host
        res.end("host: " + host );

    }
//if url contains listFiles in current directory
    else if (req.url.startsWith('/ListFiles')) {

        //get the current directory
        var currentDir = __dirname;

        //get the list of files in the current directory
        var files = fs.readdirSync(currentDir);

        //return the list of files
        res.end(files.toString());

    }
    else if (req.url.startsWith('/GetFullTextFile')) {

        //read sample.txt and return lines that contains the word "Fusce"
        var text = fs.readFileSync('sample.txt', 'utf-8');
        var lines = text.split("\r");

        var linesFound = "";
        for (var i = 1; i < lines.length; i++) {
            if (lines[i].includes("Fusce")) {
                linesFound = linesFound + lines[i] + ", ";
            }
        }

        res.end(linesFound);


    }
    else if (req.url.startsWith('/GetLineByLinefromtTextFile')) {
    
        //read sample.txt line by line
        var lineReader = require('readline').createInterface({
            input: require('fs').createReadStream('sample.txt')
        });

        //create a promise to read the file line by line, and return a list of lines that contains the word "Fusce"
        var promise = new Promise(function (resolve, reject) {
            var lines = [];
            lineReader.on('line', function (line) {
                if (line.includes("Fusce")) {
                    lines.push(line);
                }
            });
            lineReader.on('close', function () {
                resolve(lines);
            });
        });

        //return the list of lines
        promise.then(function (lines) {
            res.end(lines.toString());
        });
    }
    else if (req.url.startsWith('/CalculateMemoryConsumption')) {
    
            //return the memory consumption of the process in GB, rounded to 2 decimals
            var memory = process.memoryUsage().heapUsed / 1024 / 1024;

            res.end(memory.toFixed(2) + " GB");

    }
    else if (req.url.startsWith('/MakeZipFile')) {

        //using zlib create a zip file called sample.gz that contains sample.txt
        var zlib = require('zlib');

        var gzip = zlib.createGzip();
        var input = fs.createReadStream('sample.txt');
        var output = fs.createWriteStream('sample.gz');

        input.pipe(gzip).pipe(output);

        res.end("sample.gz created");

    }
    else if (req.url.startsWith('/RandomEuropeanCountry')) {

        //make an array of european countries and its iso codes
        var countries = [
            { country: "Italy", iso: "IT" },
            { country: "France", iso: "FR" },
            { country: "Spain", iso: "ES" },
            { country: "Germany", iso: "DE" },
            { country: "United Kingdom", iso: "GB" },
            { country: "Greece", iso: "GR" },
            { country: "Portugal", iso: "PT" },
            { country: "Romania", iso: "RO" },
            { country: "Bulgaria", iso: "BG" },
            { country: "Croatia", iso: "HR" },
            { country: "Czech Republic", iso: "CZ" },
            { country: "Denmark", iso: "DK" },
            { country: "Estonia", iso: "EE" },
            { country: "Finland", iso: "FI" },
            { country: "Hungary", iso: "HU" },
            { country: "Ireland", iso: "IE" },
            { country: "Latvia", iso: "LV" },
            { country: "Lithuania", iso: "LT" },
            { country: "Luxembourg", iso: "LU" },
            { country: "Malta", iso: "MT" },
            { country: "Netherlands", iso: "NL" },
            { country: "Poland", iso: "PL" },
            { country: "Slovakia", iso: "SK" },
            { country: "Slovenia", iso: "SI" },
            { country: "Sweden", iso: "SE" },
            { country: "Belgium", iso: "BE" },
            { country: "Austria", iso: "AT" },
            { country: "Switzerland", iso: "CH" },
            { country: "Cyprus", iso: "CY" },
            { country: "Iceland", iso: "IS" },
            { country: "Norway", iso: "NO" },
            { country: "Albania", iso: "AL" },
            { country: "Andorra", iso: "AD" },
            { country: "Armenia", iso: "AM" },
            { country: "Azerbaijan", iso: "AZ" },
            { country: "Belarus", iso: "BY" },
            { country: "Bosnia and Herzegovina", iso: "BA" },
            { country: "Georgia", iso: "GE" },
            { country: "Kazakhstan", iso: "KZ" },
            { country: "Kosovo", iso: "XK" },
            { country: "Liechtenstein", iso: "LI" },
            { country: "Macedonia", iso: "MK" },
            { country: "Moldova", iso: "MD" },
            { country: "Monaco", iso: "MC" },
            { country: "Montenegro", iso: "ME" },
            { country: "Russia", iso: "RU" },
            { country: "San Marino", iso: "SM" },
            { country: "Serbia", iso: "RS" },
            { country: "Turkey", iso: "TR" },
            { country: "Ukraine", iso: "UA" },
            { country: "Vatican City", iso: "VA" }
        ];

        //return a random country from the array
        var randomCountry = countries[Math.floor(Math.random() * countries.length)];

        //return the country and its iso code
        res.end(randomCountry.country + " " + randomCountry.iso);

    }
    else if (req.url.startsWith('/Get')) {
        const { query } = url.parse(req.url, true);
        const { key } = query;

        if (!key) {
            res.end('key not passed');
        } else {
            res.end('hello ' + key);
        }
    } 
    else {
        res.end('Called method not found');
    }





});

server.listen(3000, () => {
    console.log('server is listening on port 3000');
});




//write command line to generate package.json
//npm init -y

//write curl command to getMoviesByDirector
//curl http://localhost:3000/getMoviesByDirector?director=Quentin%20Tarantino

