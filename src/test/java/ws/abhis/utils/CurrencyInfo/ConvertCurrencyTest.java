package ws.abhis.utils.CurrencyInfo;

import junit.framework.TestCase;
import ws.abhis.utils.CurrencyInfo.*;

public class ConvertCurrencyTest extends TestCase {
	public void testAppUpdate() {
		try {
			StartApplication.main(null);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
}
