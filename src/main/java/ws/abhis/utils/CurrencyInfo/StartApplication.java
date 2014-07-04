package ws.abhis.utils.CurrencyInfo;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.twilio.sdk.TwilioRestException;

public class StartApplication {
	
	private static final Logger logger = LogManager.getLogger(StartApplication.class);
	
	public static void main(String[] args) {
		ConvertCurrency objConveryCurrency = new ConvertCurrency();
		try {
			String sid = objConveryCurrency.sendSms();
			logger.info("Completed with sid " + sid);
		} catch (TwilioRestException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
