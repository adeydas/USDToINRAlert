package ws.abhis.utils.CurrencyInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.twilio.sdk.TwilioRestException;

import junit.framework.TestCase;

public class ConvertCurrencyTest extends TestCase {
	public void testGetInr() {
		ConvertCurrency objConvertCurrency = new ConvertCurrency();
		try {
			double s = objConvertCurrency.getInr();
			System.out.println(s);
			assertTrue(true);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testSendSms() {
		ConvertCurrency obj = new ConvertCurrency();
		try {
			obj.sendSms();
			assertTrue(true);
		} catch (TwilioRestException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
