USD To INR Alert Program
========================

A simple program to send USD to INR conversion rates regularly via SMS using Twilio.

Sends the current rate along with a line chart of fluctuation based on historical data.


Example configuration file (config.json)
-----------------------------------------

	{

    	"twilioAccountSid": "xxxxxxxxxxxxxxx",
    
    	"twilioAuthToken": "xxxxxxxxxxxxxxx",
    
    	"toNumber": "+1xxxxxxxxxxx",
    
    	"fromNumber": "+1xxxxxxxxx",
    
    	"htmlPath": "/var/www/html/",
    
    	"webBasePath": "http://example.com/",
    
    	"currencyDataFilename": "savedData.dat"
    
	}
	

How to compile and deploy
-------------------------

Compile the source code and package it using the maven assembly plugin. Command is:

	mvn assembly:single
	
Deploy the jar and the config.json file to your webserver and set up a cron job with the frequency 
with which you want to receive texts.

NOTE: More the frequency, better would be the line chart.



Author: Abhishek Dey Das

Contact: abhishek@abhis.ws