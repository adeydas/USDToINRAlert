package ws.abhis.utils.CurrencyInfo;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.sdk.TwilioRestException;

public class StartApplication {
	
	private static final Logger logger = LogManager.getLogger(StartApplication.class);
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//Initialize configuration
		File f = new File("config.json");
		if (!f.exists()) {
			throw new IOException("Configuration file not found");
		}
		ObjectMapper mapper = new ObjectMapper();
		Configuration config = mapper.readValue(new File("config.json"), Configuration.class);
		
		ConvertCurrency objConveryCurrency = new ConvertCurrency(config);
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
