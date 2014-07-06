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

Author: Abhishek Dey Das

Contact: abhishek@abhis.ws