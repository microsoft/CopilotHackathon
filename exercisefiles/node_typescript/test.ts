//write npm command line to install mocha
//command to run this test file
//mocha test.js

import * as assert from 'assert';
import * as http from 'http';
import server from './nodeserver';

describe('Node Server', () => {
    it('should return "key not passed" if key is not passed', (done: Mocha.Done) => {
        http.get('http://localhost:3000/get', (res: http.IncomingMessage) => {
            let data = '';
            res.on('data', (chunk: string) => {
                data += chunk;
            });
            res.on('end', () => {
                assert.strictEqual(data, 'key not passed');
                done();
            });
        });
    });

    //add test to check get when key is equal to world

    //add test to check validatephoneNumber

    //write test to validate validateSpanishDNI
   

    //write test for returnColorCode red should return code #FF0000


   //write test for daysBetweenDates



});
