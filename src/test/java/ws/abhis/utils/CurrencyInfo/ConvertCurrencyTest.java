package ws.abhis.utils.CurrencyInfo;

import junit.framework.TestCase;

public class ConvertCurrencyTest extends TestCase {
	public void testAppUpdate()  {
		try {
			StartApplication.main(new String[] {"update"});
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} 
	}
	
	public void testAppSend() {
		try {
			StartApplication.main(new String[] {"send"});
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		} 
	}
}
