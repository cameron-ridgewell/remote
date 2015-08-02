#!/usr/bin/env node

var http 	= require('http');
var sys 	= require('sys');
var exec 	= require('child_process').exec;
var cheerio	= require('cheerio'); 
var request = require("request");

//curl -d 'data' localhost:PORT

var serverURL = "http://remoteserver-wintra.rhcloud.com";
var contactURL = "";
var infoURL = "http://localhost:4040";
const PORT = 8080;

function handleRequest(req,res) {
	req.setEncoding('utf8');
	req.on('data', function (chunk) {
	    console.log('BODY: ' + chunk);
	});
	res.end('Req URL ' + req.url +'\n');
}

var server = http.createServer(handleRequest);

server.listen(PORT, function() {
	console.log("Server listening on http://localhost:%s",PORT);

	function puts(error, stdout, stderr) { sys.puts(stdout) }
	exec("./ngrok http " + PORT, puts);

	request(infoURL, function (error, response, body) {
		if (!error) {
			var $ = cheerio.load(body),
				scripts = $("#content").html(body);
				index = ("" + scripts).indexOf('URL\\');
				if (index > -1) {
					contactURL = ("" + scripts).substring(index + 8, index + 33);

					request({
							uri: serverURL + "/BBB/",
							method: "POST",
							form: {contactURL: contactURL},
							qs: {command: 'reassignURL'}
						}, function(error, response, body) {
					  		console.log(body);
						});

				} else {
					console.log("No Hostname found!");
				}
		} else {
			console.log("We’ve encountered an error: " + error);
		}
	});
})