package ws.abhis.utils.CurrencyInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertCurrency {
	private static final String uri = "http://rate-exchange.appspot.com/currency?from=usd&to=inr";
	private static final String USER_AGENT = "abhis.ws";
	private static final Logger logger = LogManager.getLogger();
	private static final String ACCOUNT_SID = "AC5a016b801a8131bd96bc3e6c7e9a04cf";
	private static final String AUTH_TOKEN = "76c167f7f5372c8722b4d72d76181da3";
	private static final String toNumber = "+16147679142";
	private static final String fromNumber = "+16147100052";
	private static final String htmlPath = "/var/www/html/visus/";

	private String getFromApi() throws IOException {
		URL obj = new URL(uri);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public double getInr() throws IOException {
		String response = getFromApi();
		logger.info("Response received: " + response);
		ObjectMapper mapper = new ObjectMapper();
		CurrencyType objCurrencyType = mapper.readValue(response,
				CurrencyType.class);
		SerializeCurrencyData objSerialize = new SerializeCurrencyData();
		objSerialize.saveCurrencyData(objCurrencyType);
		return objCurrencyType.getRate();
	}
	
	public String createVisualization() throws FileNotFoundException {
		SerializeCurrencyData obj = new SerializeCurrencyData();
		AllCurrencyData acd = obj.retrieveCurrencyData();
		VisualizationTemplate objVisu = new VisualizationTemplate();
		if (acd != null) {
			String finalHtml = objVisu.createVisualizationHtml(acd);
			Random rd = new Random();
			int nxt = rd.nextInt();
			String n = Integer.toString(nxt);
			String totalPath = this.htmlPath + n + ".html";
			PrintWriter out = new PrintWriter(totalPath);
			out.println(finalHtml);
			out.close();
			return n + ".html";
		} else {
			return null;
		}
	}

	public String sendSms() throws TwilioRestException, IOException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		double inr = getInr();
		String inr2 = Double.toString(inr);
		 String htm = "http://maven.abhis.ws/visus/" + createVisualization();
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", "Todays rate: " + inr2 + ". Visu: " + htm));
	    params.add(new BasicNameValuePair("To", toNumber));
	    params.add(new BasicNameValuePair("From", fromNumber)); 
	     
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    return message.getSid();
	}
}
