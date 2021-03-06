package ws.abhis.utils.CurrencyInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertCurrency {
	private String uri = "https://openexchangerates.org/api/latest.json?app_id=";
	private static final String USER_AGENT = "abhis.ws";
	private static final Logger logger = LogManager.getLogger();
	private Configuration config;

	public ConvertCurrency(Configuration config) {
		this.config = config;
		uri = uri + config.getOpenexchangerateAppId();
	}

	private String getFromApi() throws IOException {
		// uri = uri + config.getOpenexchangerateAppId();
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

	public double getInr() throws IOException, TwilioRestException {

		String response = getFromApi();
		logger.info("Response received: " + response);
		ObjectMapper mapper = new ObjectMapper();
		CurrencyType objCurrencyType = mapper.readValue(response,
				CurrencyType.class);

		SerializeCurrencyData objSerialize = new SerializeCurrencyData(config);
		objSerialize.saveCurrencyData(objCurrencyType);
		Double d = objCurrencyType.getRates().get("INR");
		checkToSend(d);
		return d;
	}

	private void checkToSend(Double currentRate) throws TwilioRestException,
			IOException {
		SerializeCurrencyData objSerialize = new SerializeCurrencyData(config);
		AllCurrencyData cd = objSerialize.retrieveCurrencyData();
		List<CurrencyType> allData = cd.allData;
		double total = 0.0;
		double average = 0.0;
		int i = 0;
		for (i = 0; i < allData.size(); i++) {
			double d = allData.get(i).getRates().get("INR");
			if (d > total) {
				total = d;
			}
			average += d;
		}

		String message = "";
		average = average / (i + 1);
		if (currentRate >= total) {
			message = "RED ALERT: Current rate is greater than highest historical rate. Rate: "
					+ currentRate;
			sendSms(currentRate, message);
			sendPushMessage(currentRate, message);
		}
		if (currentRate >= average) {
			message = "YELLOW ALERT: Current rate is greater than historical average. Rate: "
					+ currentRate + " Average: " + average;
			// sendSms(currentRate, message);
			sendPushMessage(currentRate, message);
		}
	}

	public String createVisualization() throws IOException {
		SerializeCurrencyData obj = new SerializeCurrencyData(config);
		AllCurrencyData acd = obj.retrieveCurrencyData();
		VisualizationTemplate objVisu = new VisualizationTemplate();
		if (acd != null && acd.allData != null) {
			String finalHtml = objVisu.createVisualizationHtml(acd);
			Random rd = new Random();
			int nxt = rd.nextInt();
			String n = Integer.toString(nxt);
			String totalPath = config.getHtmlPath() + n + ".html";
			File f = new File(totalPath);
			if (!f.exists()) {
				f.createNewFile();
			}
			PrintWriter out = new PrintWriter(totalPath);
			out.println(finalHtml);
			out.close();
			return n + ".html";
		} else {
			return null;
		}
	}

	public void sendPushMessage(Double currentRate, String messageSend)
			throws IOException {
		String htm = config.getWebBasePath() + createVisualization();
		String url = "https://api.pushover.net/1/messages.json";
		InputStream in = null;
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);

			// Add any parameter if u want to send it with Post req.
			method.addParameter("token", config.getPushOverToken());
			method.addParameter("user", config.getPushOverUser());
			method.addParameter("message", messageSend);
			method.addParameter("title", "USD to INR exchange rate alert");
			method.addParameter("url", htm);
			method.addParameter("url_title", "Visualization of data");

			int statusCode = client.executeMethod(method);

			if (statusCode != -1) {
				in = method.getResponseBodyAsStream();
			}

			logger.info("Received from push over: " + in);

		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String sendSms(Double currentRate, String messageSend)
			throws TwilioRestException, IOException {
		TwilioRestClient client = new TwilioRestClient(
				config.getTwilioAccountSid(), config.getTwilioAuthToken());
		// double inr = getInr();
		// String inr2 = Double.toString(inr);
		String htm = config.getWebBasePath() + createVisualization();

		String numbers = config.getToNumber();
		String[] nos = null;
		if (!numbers.contains(",")) {
			nos = new String[]{numbers};
		} else {
			String[] f = numbers.split(",");
			nos = new String[f.length];
			nos = f;
		}
		
		String messageIds = "";
		for (int i = 0; i < nos.length; i++) {
			// Build a filter for the MessageList
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Body", messageSend + " Chart: "
					+ htm));
			params.add(new BasicNameValuePair("To", nos[i]));
			params.add(new BasicNameValuePair("From", config.getFromNumber()));

			MessageFactory messageFactory = client.getAccount()
					.getMessageFactory();
			Message message = messageFactory.create(params);
			messageIds += message.getSid();
		}
		return messageIds;
	}
}
