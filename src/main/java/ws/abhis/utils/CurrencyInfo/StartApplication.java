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
	
	public static void main(String[] args) throws Exception {
		if (args.length<=0) {
			throw new Exception("Pass argument update or send");
		}
		
		String param = args[0];
		
		//Initialize configuration
		File f = new File("config.json");
		if (!f.exists()) {
			throw new IOException("Configuration file not found");
		}
		ObjectMapper mapper = new ObjectMapper();
		Configuration config = mapper.readValue(new File("config.json"), Configuration.class);
		
		ConvertCurrency objConveryCurrency = new ConvertCurrency(config);
		try {
			param = param.toLowerCase().trim();
			if (param.equals("update")) {
				objConveryCurrency.getInr();
			} else if (param.equals("send")) {
				String sid = objConveryCurrency.sendSms();
				logger.info("Completed with sid " + sid);
			} else {
				throw new Exception("Unknown param passed");
			}
		} catch (TwilioRestException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
