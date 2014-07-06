package ws.abhis.utils.CurrencyInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.twilio.sdk.TwilioRestException;

import junit.framework.TestCase;

public class ConvertCurrencyTest extends TestCase {
	public void testApp() {
		try {
			StartApplication.main(null);
			assertTrue(true);
		} catch (JsonParseException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
}
