const express = require('express');
const request = require('request');
const bodyParser = require('body-parser');
const admin = require('firebase-admin');
const serviceAccount = require("C:/Users/User/Desktop/milab_examples/stockmonitor-75886-firebase-adminsdk-ifzc6-8125900d43.json");
const EventEmitter = require('events').EventEmitter;

let intervalId = -1;
let STOCKS = {};
let TOKENS = {};
const PORT = 8080;
let app = express();
app.use(bodyParser.json());

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://stockmonitor-75886.firebaseio.com"
});



app.post('/initToken', (req, res) => {
    console.log("token arrived:", req.body.token);
    return res.status(200);
});



app.post('/stockPrice', (req, res, next) => {
    TOKENS[req.body.user] = req.body.token;
    STOCKS[req.body.user] = req.body.stock;
    if(intervalId != -1){
        clearInterval(intervalId);
    }
	let e = new fetchEveryFifteen();
	e.on('fetchEveryFifteen', function(){
        startStockPriceUpdates(req.body.user);
	});
    e.start();
    return res.status(200);
});


class fetchEveryFifteen extends EventEmitter{
	start(){
		intervalId = setInterval(this.emit.bind(this, 'fetchEveryFifteen'), 15000); //will invoke this function every 15 sec 
	}
}


function startStockPriceUpdates(user) {
    console.log("startStockPriceUpdates was called");
    let apiKey = 'Y2COS59E0K1MMGOA';
    let STOCK = STOCKS[user];
    console.log("STOCKS[user]", STOCKS[user]);
    let url = `https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=${STOCK}&apikey=${apiKey}`

    request(url, function (err, response, body) {
        if(err){
            console.log('error:', error);
            return res.error(err);
          } else {
            let stockInfo = JSON.parse(body);
            if (!stockInfo || !stockInfo["Global Quote"]){
				return;
			}
            let globalQuote = "Global Quote";
            let sectionFive = "05. price";
            let stockPrice = stockInfo[globalQuote][sectionFive];

            // This registration token comes from the client FCM SDKs.
            let registrationToken = TOKENS[user];
            let message = {
            "token": registrationToken,
            "data": {
                 "stockPrice" : stockPrice
         },
       
        };

        // Send a message to the device corresponding to the provided
        // registration token.
        admin.messaging().send(message)
        .then((response) => {
        // Response is a message ID string.
            console.log('Successfully sent message:', response);
        })
        .catch((error) => {
        console.log('Error sending message:', error);
        });
          }
    });
    return;
}

app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}`);
});
