#!/usr/bin/env node

var http 	= require('http');
var request = require('request');
var ngrok 	= require('ngrok');
//var bone    = require('bonescript');
//curl -d 'data' localhost:PORT

var serverURL = "http://remoteserver-wintra.rhcloud.com";
var contactURL = "";
var infoURL = "http://localhost:4040";

var state = true;

var server = http.createServer(handleRequest);

function handleRequest(req,res) {
	req.setEncoding('utf8');
	req.on('data', function (chunk) {
	    console.log('BODY: ' + chunk);
	    state = !state;
	});
	res.end('Req URL ' + req.url +'\n');
}

server.listen(0, function() {
	var port = server.address().port
	console.log("Server listening on http://localhost:%s", port);

	ngrok.connect(port, function(err, url) {
		if (err) {
			console.log(err);
		} else {
			console.log(url);
			contactURL = url;
			request({
					uri: serverURL + "/BBB/",
					method: "POST",
					form: {contactURL: contactURL},
					qs: {command: 'reassignURL'}
				}, 
				function(error, response, body) {
				    if(!error) {
		  			    console.log(body);
				    } else {
				        console.log(error);
				    }
				});
		}
	});
})