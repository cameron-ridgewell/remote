#!/usr/bin/env node

var http                = require('http');
var request             = require('request');
var sys                 = require('util');
var exec                = require('child_process').exec;
var passport            = require('passport');
var url                 = require('url');
var child;

var port = 8081;
var server = http.createServer(handleRequest);

var tv = 'VIZIO_REMOTE';
var av = 'YAMAHA_REMOTE';
var cm = 'COMCAST_REMOTE';

var hdmiPorts = {};
hdmiPorts['Television'] = '0'; //DOES NOT CURRENTLY HAVE A VALUE
hdmiPorts['Chromecast'] = '1';
hdmiPorts['XBox+360'] = '2';
hdmiPorts['Wii+U'] = '3';
hdmiPorts['HDMI+Aux'] = '4';

function handleRequest(req,res) {
        req.setEncoding('utf8');

        req.on('data', function (chunk) {
                console.log('BODY: ' + chunk);
        });

        var remote = "";
        var key = "";
        var queries = queryList(url.parse(req.url).query);

        switch (url.parse(req.url).pathname) {
                case '/cm/':
                        remote = cm;
                        if (queries != null) {
                                switch (queries['cmd']) {
                                        case 'power':
                                                key = 'KEY_POWER';
                                                break;
                                        case 'number':
                                                key = 'KEY_' + queries['num'];
                                                break;
                                        case 'volumeUp':
                                                key = 'KEY_VOLUMEUP';
                                                break;
                                        case 'volumeDown':
                                                key = 'KEY_VOLUMEDOWN';
                                                break;
                                        default:
                                                key = "";
                                }
                        }
                        break;
                case '/tv/':
                        remote = tv;
                        if (queries != null) {
                                switch (queries['cmd']) {
                                        case 'power':
                                                key = 'KEY_POWER';
                                                break;
                                        case 'number':
                                                key = 'KEY_' + queries['num'];
                                                break;
                                        case 'volumeUp':
                                                key = 'KEY_VOLUMEUP';
                                                break;
                                        case 'volumeDown':
                                                key = 'KEY_VOLUMEDOWN';
                                                break;
                                        default:
                                                key = "";
                                }
                        }
                        break;

                case '/av/':
                        remote = av;
                        if (queries != null) {
                                console.log(queries);
                                switch (queries['cmd']) {
                                        case 'power':
                                                key = 'KEY_POWER';
                                                break;
                                        case 'volumeUp':
                                                key = 'KEY_VOLUMEUP';
                                                break;
                                        case 'volumeDown':
                                                key = 'KEY_VOLUMEDOWN';
                                                break;
                                        case 'changeHDMI':
                                                key = 'KEY_'+ hdmiPorts[queries['port']];
                                                break;
                                        default:
                                                key = "";
                                }
                        }
                        break;
                default:
                        console.log('no command filter parameter found');
        }
        if (key != "" && remote != "") {
                irsend(remote, key);
        }

        res.end('Req URL ' + req.url +'\n');
}

server.listen(port, function() {
        console.log("Server listening on http://localhost:%s", port);
})

function irsend(remote, command) {
        var cmdstring = 'irsend SEND_ONCE ' + remote + ' ' + command;
        console.log(cmdstring);
        child = exec(cmdstring, function (error, stdout, stderr) {
                if (stdout != '' && stderr != '') {
                        sys.print('stdout: ' + stdout + '\n');
                        sys.print('stderr: ' + stderr + '\n');
                }
                if (error !== null) {
                        console.log('exec error: ' + error);
                }
        });
}

function queryList(queries) {
        var dict = {};
        if (queries == '') {
                return null;
        } else {
                try {
                        var querylist = queries.split('&');
                        for (var i = 0; i <  querylist.length; i++) {
                                key_value = querylist[i].split('=');
                                dict[key_value[0]]=key_value[1];
                                console.log('Parameter: %s found: %s',key_value[0],dict[key_value[0]]);
                        }
                        return dict;
                } catch (err) {
                        console.log('Invalid query parameters!');
                        console.log(err.message);
                        return null;
                }
        }
}
