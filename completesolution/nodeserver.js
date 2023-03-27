// write a nodejs server that will expose a method call "get" that will return the value of the key passed in the query string
// example: http://localhost:3000/get?key=hello
// should return the value of foo
// if the key is not passed, return "key not passed"
// if the key is passed, return "hello" key

const http = require('http');
const url = require('url');
const fs = require('fs');

const server = http.createServer((req, res) => {


    
     if (req.url.startsWith('/get')) {
        const { query } = url.parse(req.url, true);
        const { key } = query;

        if (!key) {
            res.end('key not passed');
        } else {
            res.end('hello ' + key);
        }
    } else if (req.url.startsWith('/daysBetweenDates')) {
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

    }     else if (req.url.startsWith('/validatephonenumber')) {

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
    } else if (req.url.startsWith('/validateSpanishDNI')) {
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
    } else if (req.url.startsWith('/returnColorCode')) {

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

    } else {
        res.end('not found');
    }

});

server.listen(3000, () => {
    console.log('server is listening on port 3000');
});


//write command line to generate package.json
//npm init -y

