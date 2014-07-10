USD To INR Alert Program
========================

A simple program to send USD to INR conversion rates regularly via SMS using Twilio.

Sends the current rate along with a line chart of fluctuation based on historical data.

The program uses Open Exchange Rate (https://openexchangerates.org) to get the current exchange rate 
and Twilio (http://twilio.com) to send SMS's. 

New updates
-----------

Instead of sending a text message periodically, now sends a text message when the exchange rate is equal or 
greater than the highest exchange rate based on historical data. It also sends a text message if the exchange rate 
is greater than the historical average.

Added PushOver support (http://pushover.net) to receive push message as well.


Example configuration file (config.json)
-----------------------------------------

	{

    	"twilioAccountSid": "xxxxxxxxxxxxxxx",
    
    	"twilioAuthToken": "xxxxxxxxxxxxxxx",
    
    	"toNumber": "+1xxxxxxxxxxx",
    
    	"fromNumber": "+1xxxxxxxxx",
    
    	"htmlPath": "/var/www/html/",
    
    	"webBasePath": "http://example.com/",
    
    	"currencyDataFilename": "savedData.dat",
    	
    	"openexchangerateAppId": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    	
    	"pushOverToken": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    	
    	"pushOverUser": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    
	}
	

How to compile and deploy
-------------------------

Compile the source code and package it using the maven assembly plugin. Command is:

	mvn assembly:single
	
Setup a CRON job to run the jar periodically.


Author: Abhishek Dey Das

Contact: http://abhis.ws / http://adeydas.com