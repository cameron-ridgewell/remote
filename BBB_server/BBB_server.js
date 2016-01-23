#!/usr/bin/env node

var http    = require('http');
var request = require('request');
var parseXML = require('xml2js').parseString;

var yamahaURL = '192.168.0.105';
var port;
var server = http.createServer(handleRequest);

const STATUS_CHECK_DELAY = 180;

//Input Command Handler
function handleRequest(req, res) {
    req.setEncoding('utf8');
    req.on('data', function (chunk) {
        console.log('BODY: ' + chunk);
        state = !state;
    });
    res.end('Req URL ' + req.url +'\n');
}

//AV Command Outputs
function getAVPowerStatus(res) {
    console.log('Get Power Status');
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="GET"><System><Power_Control><Power>' +
        'GetParam</Power></Power_Control></System></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        res(resObj.YAMAHA_AV.System[0].Power_Control[0].Power[0]);
    });
}

function setAVPowerStatus(status, res) {
    console.log('Set Power Status: ' + status);
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="PUT"><Main_Zone><Power_Control><Power>' +
        status + '</Power></Power_Control></Main_Zone></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        setTimeout(function() {
            getAVPowerStatus(function(resObj2){});
        }, STATUS_CHECK_DELAY);
        res(resObj);
    });
}

function getAVChannel(res) {
    console.log('Get Channel');
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="GET"><Main_Zone><Input><Input_Sel>' + 
        'GetParam</Input_Sel></Input></Main_Zone></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        res(resObj.YAMAHA_AV.Main_Zone[0].Input[0].Input_Sel[0]);
    });
}

function setAVChannel(channel, res) {
    console.log('Set Channel: ' + channel);
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="PUT"><Main_Zone><Input><Input_Sel>' +
        channel + '</Input_Sel></Input></Main_Zone></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        setTimeout(function() {
            getAVChannel(function(resObj2){ console.log(resObj2)});
        }, STATUS_CHECK_DELAY);
        res(resObj);
    });
}

function getAVVolume(res) {
    console.log('Get Volume');
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="GET"><Main_Zone><Volume><Lvl>' +
        'GetParam</Lvl></Volume></Main_Zone></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        var lvl = resObj.YAMAHA_AV.Main_Zone[0].Volume[0].Lvl[0];
        var volume = 80.5 + (lvl.Val[0] / Math.pow(10,lvl.Exp[0]));
        res(volume);
    });
}

function setAVVolume(volume, res) {
    console.log('Set Volume: ' + volume + 'dB');
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="PUT"><Main_Zone><Volume><Lvl><Val>-' +
        (805 - volume * 10) + '</Val><Exp>1</Exp><Unit>dB</Unit></Lvl>' +
        '</Volume></Main_Zone></YAMAHA_AV>';
    sendAVrequest(body, function(resObj) {
        setTimeout(function() {
            getAVVolume(function(resObj2){});
        }, STATUS_CHECK_DELAY);
        res(resObj);
    });
}

function aVVolumeStep(direction, res) {
    console.log('Volume: ' + direction);
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="PUT"><Main_Zone><Volume><Lvl><Val>' + 
        direction + '</Val><Exp></Exp><Unit></Unit></Lvl>' + 
        '</Volume></Main_Zone></YAMAHA_AV>';

    sendAVrequest(body, function(resObj) {
        setTimeout(function() {
            getAVVolume(function(resObj2){});
        }, STATUS_CHECK_DELAY);
        res(resObj);
    });
}

function aVMute(res) {
    console.log('Volume Mute');
    var body = '<?xml version="1.0" encoding="utf-8"?>' +
        '<YAMAHA_AV cmd="PUT"><Main_Zone><Volume><Mute>On/Off' + 
        '</Mute></Volume></Main_Zone></YAMAHA_AV>';

    sendAVrequest(body, function(resObj) {
        setTimeout(function() {
            getAVVolume(function(resObj2){});
        }, STATUS_CHECK_DELAY);
        res(resObj);
    });
}

function sendAVrequest(body, objBehavior) {
    var postRequest = {
        host: yamahaURL,
        path: '/YamahaRemoteControl/ctrl',
        method: 'POST',
        headers: {
            'Content-Type': 'text/xml',
            'Content-Length': Buffer.byteLength(body)
        }
    };

    var req = http.request(postRequest, function (res) {
        
        var buffer = "";
        res.on("data", function(data) { buffer = buffer + data; } );
        res.on("end", function(data) { 
            parseXML(buffer, function(err, result) {
                if (err) {
                    console.log(err);
                }
                else {
                    objBehavior(result);
                }
            });
        });
    });

    req.on('error', function(e) {
        console.log('problem with request: ' + e.message);
    });

    req.write(body);
    req.end();
}

server.listen(0, function() {
    //port = server.address().port;
    //console.log("Server listening on http://localhost:%s", port);
    aVMute(function(res) { console.log(res)});
});