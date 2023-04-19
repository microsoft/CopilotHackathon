//write npm command line to install mocha
//npm install --global mocha

//command to run this test file
//mocha test.js

const assert = require('assert');
const http = require('http');

const server = require('./NodeServer');



describe('Node Server', () => {
    it('should return "key not passed" if key is not passed', (done) => {
        http
        .get('http://localhost:3000/Get' , (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'key not passed');
                done();
            });
        });
    });

    
    it('should return the value of the key if key is found', (done) => {
        http.get('http://localhost:3000/Get?key=world', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'hello world');
                done();
            });
        });
    });

    //add test to check validatephoneNumber
    it('should return "valid" if phoneNumber is valid', (done) => {
        http.get('http://localhost:3000/Validatephonenumber?phoneNumber=34666666666', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'valid');
                done();
            });
        });
    });

    //write test to validate spanish DNI
    it('should return "valid" if spanish DNI 86471508H is valid', (done) => {
        http.get('http://localhost:3000/ValidateSpanishDNI?dni=86471508H', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'valid');
                done();
            });
        });
    });


    
    //write test to validate spanish DNI
    it('should return "valid" if spanish DNI 24153149K is valid', (done) => {
        http.get('http://localhost:3000/ValidateSpanishDNI?dni=24153149K', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'valid');
                done();
            });
        });
    });


    //write test to validate spanish DNI
    it('should return "valid" if spanish DNI 12345678A is invalid', (done) => {
        http.get('http://localhost:3000/ValidateSpanishDNI?dni=12345678A', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, 'invalid');
                done();
            });
        });
    });

    //write test for returnColorCode
    it('should return "red" if color is red', (done) => {
        http.get('http://localhost:3000/ReturnColorCode?color=red', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, '#FF0000');
                done();
            });
        });
    });

   //write test for daysBetweenDates
    it('should return "1" if dates are 2020-01-01 and 2020-01-02', (done) => {
        http.get('http://localhost:3000/DaysBetweenDates?date1=2020-01-01&date2=2020-01-02', (res) => {
            let data = '';
            res.on('data', (chunk) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.equal(data, '1 days');
                done();
            });
        });
    });


});